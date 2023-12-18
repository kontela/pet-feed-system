#include <SoftwareSerial.h>
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <WiFiClient.h>

SoftwareSerial arduino(D1,D2);

ESP8266WebServer server(80); 

const char* ssid = "hakan";
const char* password = "petcare123";

// It is our server for the arduino :) it makes our arduino defacto server. (arduino do not have built in wifi)

void setup(){

  Serial.begin(9600);
  arduino.begin(9600);

    WiFi.begin(ssid, password); 

    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.println("Connecting to WiFi...");
    }

    Serial.println("Connected to WiFi");
    Serial.print("IP Address: ");
    Serial.println(WiFi.localIP());

    //These two endpoints are for requests from Backend Server.
                                             // When backend server uses this;
    server.on("/load",handleLoadRequest);    // It will get weight data on basil of the petfood plate (for various smart use cases) 
    server.on("/servo", handleServoRequest); // It will make arduino to perform feed action.
    server.begin(); 
}



void loop(){

//this extract arduino incoming message, after extraction sends for "endpoint" seperation.
  static String incomingMessage = "";
  if(arduino.available()>0){

        char receivedChar = arduino.read();
        if (receivedChar == '<') {
            incomingMessage = "";
        } else if (receivedChar == '>') {
            handleMessage(incomingMessage);
            incomingMessage = "";
        } else {
            incomingMessage += receivedChar;
        }

 
  }
  server.handleClient(); 

}

//Here evaluates the incoming message from arduino, it is virtually like endpoints from arduino to the esp32. Only instead of HTTP, it is string message :)
void handleMessage(String message) {
    Serial.println(message);
    if (message.length() > 0) {
        // Check message type
        if (message.startsWith("S;")) {
            // Handle sensor data
            String sensorData = message.substring(2); 
            //handleSensorData(sensorData);
        } else if (message.startsWith("W;")) {
            // Handle weight data
            String weightData = message.substring(2); 
            handleWeightData(weightData);
        } else {
            // Unknown message type
            Serial.println("Unknown message type received: " + message);
        }
    }
}

void handleSensorData(String data) {
    Serial.println("Sensor Data: " + data);
    
    WiFiClient client;
    HTTPClient http; 
    http.begin(client,"http://192.168.101.3/sendPhoto"); // this is ESP32 Camera endpoint
    int httpCode = http.GET();                           // It will trigger ESP32 to capture photo
                                                         // ESP32 will send captured photo to object detection server for processing.
                                                         // And if the object detected, object detection server will send it to backend server.
                                                         // Hence on mobile app user will be able to see latest his/her cat taken photo. 
    if (httpCode > 0) {
        String payload = http.getString();
        Serial.println("HTTP Response code: " + String(httpCode));
        Serial.println("Response: " + payload);
    } else {
        Serial.println("Error on HTTP request");
    }

    http.end(); 

void handleWeightData(String data) {
    Serial.println("Weight Data: " + data);

    WiFiClient client;
    HTTPClient http;
    http.begin(client,"http://192.168.101.76:8080/api/log/update"); 
    http.addHeader("Content-Type", "application/json");

    StaticJsonDocument<200> jsonDocument;
    jsonDocument["weight"] = data;
    String requestBody;
    serializeJson(jsonDocument, requestBody);

    int httpCode = http.POST(requestBody);

    if (httpCode > 0) {
        String payload = http.getString();
        Serial.println("HTTP Response code: " + String(httpCode));
        Serial.println("Response: " + payload);
    } else {
        Serial.println("Error on HTTP request");
    }

    http.end(); 
}

void handleServoRequest() {
    Serial.println("Servo motor request received");

    // simple  response
    server.send(200, "text/plain", "Servo motor command received");
      
      
    //Arduino recieves this, and with its business logic, it performs weigh evaluation.
    arduino.println("motor");
}

void handleLoadRequest(){
    Serial.println("Load request received");

    // simple  response
    server.send(200, "text/plain", "Load command received");

    //arduino recieves this, and with its business logic, it calculates weight of basil and then it will return weight data to here ESP8266. 
    //handleWeightData function handles incoming weight data, sends it to the backend server and it performs action.
    arduino.println("load");

}



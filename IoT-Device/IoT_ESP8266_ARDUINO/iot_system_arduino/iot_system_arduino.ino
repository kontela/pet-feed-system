
#include <HX711_ADC.h>
#if defined(ESP8266)|| defined(ESP32) || defined(AVR)
#include <EEPROM.h>
#endif


#include <Stepper.h>    

#include<SoftwareSerial.h>

SoftwareSerial arduino(12,13);

int buzzerPin = 7;
int lamba = 6;
#define do_ 261
#define re 294
#define mi 329
#define fa 349
#define sol 392
#define la 440
#define si 493
#define DO_ 523

int sarki[] = {mi,mi,fa,sol,sol,fa,mi,re,do_,do_,re,mi};


int IN1 = 11;           
int IN2 = 10;
int IN3 = 9;
int IN4 = 8;
 
int tamtur = 2048;      //  tam turunu 2048 adımda tamamlıyor
 
Stepper step_motorum = Stepper(tamtur, IN2, IN4, IN1, IN3); 


//pins:
const int HX711_dout = 4; 
const int HX711_sck = 5; 

//HX711 constructor:
HX711_ADC LoadCell(HX711_dout, HX711_sck);

const int calVal_eepromAdress = 0;
unsigned long t = 0;
String command; 

#define trigPin 3
#define echoPin 2
long sure, mesafe;
long arr[20];
int index = 0;
bool cat  = false;
int deg = 0;

void setup() {
  pinMode(buzzerPin,OUTPUT);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

  pinMode(lamba,OUTPUT);

  Serial.begin(9600); delay(10);
  Serial.println();
  arduino.begin(9600);
  LoadCell.begin();
  
  float calibrationValue; 
  calibrationValue = 696.0; 
#if defined(ESP8266)|| defined(ESP32)
  EEPROM.begin(512); 
#endif
  EEPROM.get(calVal_eepromAdress, calibrationValue); 

  unsigned long stabilizingtime = 2000; 
  boolean _tare = true; 
  LoadCell.start(stabilizingtime, _tare);
  if (LoadCell.getTareTimeoutFlag()) {
    while (1);
  }
  else {
    LoadCell.setCalFactor(calibrationValue);
  }
}

void loop() {



// AĞIRLIK İÇİN KOD KISMI

  static boolean newDataReady = 0;
  
  if (LoadCell.update()) newDataReady = true;

  command = arduino.readStringUntil('\n');

  command.trim();

  Serial.print("gelen komut:");
  Serial.println(command);
  
  if (command.equals("load")) {
      float i = LoadCell.getData();
      Serial.print("Load_cell output val: ");
      Serial.println(i);
      arduino.print("<W;");
      arduino.print(i);
      arduino.print(">");
  }

// MOTOR İÇİN KOD KISMI
   if(command.equals("motor")){

    for (int i = 0; i < sizeof(sarki)/sizeof(int); i++) {
          digitalWrite(lamba, HIGH);
          delay(300);
          tone(buzzerPin, sarki[i]);
          digitalWrite(lamba, LOW);
          delay(350);
      }
      noTone(buzzerPin);
      delay(500);

      step_motorum.setSpeed(10);  
      step_motorum.step(tamtur/2); 
  }

// KEDİ ALGILAMA İÇin KOD KISMI

digitalWrite(trigPin, LOW);
delayMicroseconds(3);
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
sure = pulseIn(echoPin, HIGH);
mesafe = (sure/2) * 0.0343;

for(int i = 19;i>0;i--){
  arr[i] = arr[i-1];
}
arr[0] = mesafe;
deg = 0;

for(int i = 0 ;i<3;i++){
    if(arr[i]<arr[i+1]){
      deg++;
    }
}

if(deg >=3){
  Serial.println("Kedi Geldi");
  arduino.print("<S;");
  arduino.print("Cat");
  arduino.print(">");
}


}

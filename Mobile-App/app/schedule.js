import { StyleSheet, Text, View } from "react-native";
import React from "react";
import { Link } from "expo-router";
import { TouchableOpacity } from "react-native-gesture-handler";
import { SafeAreaView } from "react-native";
import axios from "axios";
import { useState, useEffect } from "react";
import { DataTable } from "react-native-paper";

const fetchSchedule = async () => {
  const response = await axios.get(
    "http://192.168.101.76:8080/feeding-schedule/retrieveAll"
  );

  return response.data;
};

const schedule = () => {
  const [scheduleList, setScheduleList] = useState([]);

  const deleteSchedule = async (id) => {
    const response = await axios.get(
      "http://192.168.101.76:8080/feeding-schedule/delete/".concat(id)
    );
  };
  useEffect(() => {
    const fetchData = async () => {
      try {
        const list = await fetchSchedule();
        setScheduleList(list);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  return (
    <SafeAreaView>
      <DataTable>
        <DataTable.Header>
          <DataTable.Title>Schedule ID</DataTable.Title>
          <DataTable.Title>Schedule Date</DataTable.Title>
          <DataTable.Title>Schedule Autofeed</DataTable.Title>
          <DataTable.Title>Delete</DataTable.Title>
        </DataTable.Header>

        {scheduleList.map((item) => (
          <DataTable.Row key={item.id}>
            <DataTable.Cell>{item.id}</DataTable.Cell>
            <DataTable.Cell>{item.timeOfDay}</DataTable.Cell>
            {item.autofeed ? (
              <DataTable.Cell>true</DataTable.Cell>
            ) : (
              <DataTable.Cell>false</DataTable.Cell>
            )}
            <DataTable.Cell>
              <TouchableOpacity
                style={styles.button1}
                onPress={() => deleteSchedule(item.id)}
              >
                <Text>Delete</Text>
              </TouchableOpacity>
            </DataTable.Cell>
          </DataTable.Row>
        ))}
      </DataTable>

      <View style={styles.buttonRow}>
        <Link href="/setSchedule">
          <TouchableOpacity style={styles.button2}>
            <Text style={styles.buttonText}>Create Schedule</Text>
          </TouchableOpacity>
        </Link>
      </View>
    </SafeAreaView>
  );
};

export default schedule;

const styles = StyleSheet.create({
  buttonRow: {
    flexDirection: "row",
    justifyContent: "space-around",
    marginHorizontal: 20,
    marginBottom: 20,
    marginTop: 300,
  },
  button1: {
    backgroundColor: "red",
    width: 150,
    height: 50,
    paddingVertical: 5,
    paddingHorizontal: 5,
    borderRadius: 5,
  },
  button2: {
    backgroundColor: "#E76F51",
    width: 150,
    height: 50,
    paddingVertical: 5,
    paddingHorizontal: 5,
    borderRadius: 5,
  },
  buttonText: {
    color: "white",
    textAlign: "center",
    fontWeight: "bold",
    paddingTop: "6%",
  },
});

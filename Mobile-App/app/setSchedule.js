import { StyleSheet, TextInput, Text, View, SafeAreaView } from "react-native";
import { Button, RadioButton } from "react-native-paper";
import React, { useState, useEffect } from "react";
import { SelectList } from "react-native-dropdown-select-list";
import axios from "axios";

const setSchedule = () => {
  const [autoFeed, setAutoFeed] = useState(false);
  const [selectedHour, setSelectedHour] = useState(1);
  const [selectedMinute, setSelectedMinute] = useState("");
  const [midDay, setMidday] = useState("AM");
  const [txt, setTxt] = useState("");
  const hours = [
    { key: "1", value: 1 },
    { key: "2", value: 2 },
    { key: "3", value: 3 },
    { key: "4", value: 4 },
    { key: "5", value: 5 },
    { key: "6", value: 6 },
    { key: "7", value: 7 },
    { key: "8", value: 8 },
    { key: "9", value: 9 },
    { key: "10", value: 10 },
    { key: "11", value: 11 },
    { key: "12", value: 12 },
  ];

  const minutes = Array.from({ length: 61 }, (_, index) => ({
    key: index,
    value: index.toString().padStart(2, "0"),
  }));

  const midday = [
    { key: "1", value: "AM" },
    { key: "2", value: "PM" },
  ];

  const handlePost = async () => {
    const date = new Date();
    var dateString = "";

    if (midDay == "PM") {
      const hour = selectedHour + 12;
      dateString = dateString.concat(hour.toString());
      date.setHours(hour);
    } else {
      dateString = dateString.concat(selectedHour.toString());
      date.setHours(selectedHour);
    }
    dateString = dateString
      .concat(":")
      .concat(selectedMinute.toString())
      .concat(":00");
    date.setMinutes(selectedMinute);
    setTxt(dateString);
    const data = { timeOfDay: dateString, autoFeed: autoFeed };
    try {
      const response = await axios.post(
        "http://192.168.101.76:8080/feeding-schedule",
        data
      );
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <SafeAreaView>
      <View style={styles.inputName}>
        <Text style={{ alignSelf: "left" }}>Select Start Time</Text>
      </View>
      <View style={styles.timeSelectContainer}>
        <SelectList
          style={styles.selectList}
          placeholder="1"
          setSelected={(val) => setSelectedHour(val)}
          data={hours}
          save="value"
          search={false}
        />
        <SelectList
          style={styles.selectList}
          placeholder="00"
          setSelected={(val) => setSelectedMinute(val)}
          data={minutes}
          save="value"
          search={false}
        />
        <SelectList
          style={styles.selectList}
          placeholder="AM"
          setSelected={(val) => setMidday(val)}
          data={midday}
          save="value"
          search={false}
        />
      </View>
      <View style={{ display: "flex" }}>
        <View style={styles.autostopContainer}>
          <Text>autoFeed</Text>

          <RadioButton
            style={styles.radioButtonContainer}
            value="Autostop"
            status={autoFeed ? "checked" : "unchecked"}
            onPress={() => setAutoFeed(!autoFeed)}
            color="#264653"
          />
        </View>
        <Button
          style={{
            marginVertical: 50,
          }}
          onPress={handlePost}
        >
          Set Schedule
        </Button>
      </View>
    </SafeAreaView>
  );
};

export default setSchedule;

const styles = StyleSheet.create({
  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
  },
  box: {
    margin: 15,
  },
  container: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  selectList: {
    flex: 1,
    marginHorizontal: 5,
    marginTop: 10,
  },
  textInput: {
    flex: 1,
    maxWidth: 50,
    marginHorizontal: 5,
  },
  radioButtonContainer: {
    alignSelf: "center",
    marginBottom: 20,
  },
  timeSelectContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: 10,
    marginLeft: 30,
    marginRight: 30,
  },
  frequencyContainer: {
    marginTop: 50,
  },
  amountContainer: {
    marginTop: 20,
  },
  autostopContainer: {
    flexDirection: "row",
    marginTop: 100,
    marginLeft: 20,
  },
  inputName: {
    marginTop: 40,
    marginLeft: 20,
  },
});

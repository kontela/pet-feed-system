import {
  StyleSheet,
  Text,
  View,
  Image,
  SafeAreaView,
  Alert,
} from "react-native";
import React from "react";
import { Link } from "expo-router";
import { useState, useEffect } from "react";
import { TouchableOpacity } from "react-native-gesture-handler";
import { SelectList } from "react-native-dropdown-select-list";
import axios from "axios";

const fetchNotification = async () => {
  try {
    const response = await axios.get(
      "http://192.168.101.76:8080/Notification/retrieveAll"
    );
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

const fetchFoodAmount = async () => {
  try {
    const response = await axios.get(
      "http://192.168.101.76:8080/api/log/retrieveLatest"
    );

    return response.data.weight;
  } catch (error) {
    console.log(error);
  }
};

const fetchSinglePhoto = async (id) => {
  const response = await axios.get(
    "http://192.168.101.76:8080/api/files/retrieve/".concat(id)
  );
  return response.data;
};

const fetchPhotolist = async () => {
  const response = await axios.get(
    "http://192.168.101.76:8080/api/files/retrieve/meta"
  );

  return response.data;
};

const home = () => {
  const deviceDropdown = [
    { key: "1", value: "food" },
    { key: "2", value: "water" },
  ];

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedDevice, setSelectedDevice] = useState("food");
  const [photo, setPhoto] = useState(null);
  const [photoList, setPhotoList] = useState(null);
  const [lastSeen, setLastSeen] = useState("");
  const [foodAmount, setFoodAmount] = useState(0);
  const [notificationList, setNotificationList] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const list = await fetchNotification();
        setNotificationList(list);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    const intervalId = setInterval(fetchData, 20000);
    return () => clearInterval(intervalId);
  }, []);

  const handleForce = () => {
    const forceFeed = async () => {
      const result = await axios.post("http://192.168.101.76:8080/feed");
    };
    forceFeed();
  };

  const handleFeed = () => {
    if (foodAmount > 1) {
      Alert.alert(
        "Food Already Exists",
        "Food already exists, do you still want to feed?",
        [
          {
            text: "Cancel",
            style: "cancel",
          },
          {
            text: "Force Feed",
            style: "ok",
            onPress: handleForce,
          },
        ]
      );
    } else {
      handleForce();
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await fetchFoodAmount();
        if (result < 0) {
          setFoodAmount(0);
        } else {
          setFoodAmount(result);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
    const intervalId = setInterval(fetchData, 5000);
    return () => clearInterval(intervalId);
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const metaList = await fetchPhotolist();

        if (metaList && metaList.length > 0) {
          const sortedList = metaList.sort(
            (a, b) => new Date(b.timeOfDay) - new Date(a.timeOfDay)
          );

          const latestId = sortedList[0].id;
          const latestPhoto = await fetchSinglePhoto(latestId);
          setLastSeen(sortedList[0].timeOfDay);
          setPhoto(latestPhoto);
        }

        setPhotoList(metaList);
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      {notificationList.length > 0 &&
        Alert.alert(
          "Schedule is bypassed - ".concat(notificationList[0].timeOfOccurence),
          notificationList[0].reason,
          [
            {
              text: "Force Feed",
              style: "ok",
              onPress: handleForce,
            },
            {
              text: "Dismiss",
              style: "cancel",
            },
          ]
        )}

      <View style={styles.contentContainer}>
        <View style={styles.selectDeviceContainer}>
          {/* Change device */}
          <SelectList
            placeholder="Change device"
            data={deviceDropdown}
            search={false}
            save="value"
            setSelected={(val) => setSelectedDevice(val)}
          />
        </View>
        <View style={styles.imageContainer}>
          {/* Last seen */}
          <Text style={{ alignSelf: "center" }}>Last Seen {lastSeen}</Text>
          <Image
            source={{ uri: "data:image/jpg;base64,".concat(photo) }}
            style={styles.image}
          />
        </View>
        <View style={styles.buttonContainer}>
          {/* Buttons */}
          {selectedDevice == "food" ? (
            <>
              <View style={styles.buttonRow}>
                <Link href="/foodDisplay">
                  <TouchableOpacity style={styles.button1}>
                    <Text style={styles.buttonText}>Food Amount</Text>
                  </TouchableOpacity>
                </Link>

                <TouchableOpacity style={styles.button2} onPress={handleFeed}>
                  <Text style={styles.buttonText}>Feed</Text>
                </TouchableOpacity>
              </View>
            </>
          ) : (
            <>
              <View style={styles.buttonRow}>
                <Link href="/waterDisplay">
                  <TouchableOpacity style={styles.button1}>
                    <Text style={styles.buttonText}>Water Level: Low</Text>
                  </TouchableOpacity>
                </Link>

                <Link href="/water">
                  <TouchableOpacity style={styles.button2}>
                    <Text style={styles.buttonText}>Water</Text>
                  </TouchableOpacity>
                </Link>
              </View>
            </>
          )}
          <View style={styles.buttonRow}>
            <Link href="/schedule">
              <TouchableOpacity style={styles.button3}>
                <Text style={styles.buttonText}>Schedule</Text>
              </TouchableOpacity>
            </Link>
            <Link href="/history">
              <TouchableOpacity style={styles.button4}>
                <Text style={styles.buttonText}>History</Text>
              </TouchableOpacity>
            </Link>
          </View>
          <View style={styles.buttonRow}>
            <Link href="/lastSeens">
              <TouchableOpacity style={styles.button5}>
                <Text style={styles.buttonText}>Last Seen</Text>
              </TouchableOpacity>
            </Link>
          </View>
        </View>
      </View>
    </SafeAreaView>
  );
};

export default home;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "flex-end",
  },
  contentContainer: {
    flex: 1,
    justifyContent: "space-between",
  },
  selectDeviceContainer: {
    marginTop: 10, // Add space above the SelectList
  },
  imageContainer: {
    marginTop: 50, // Add space above the image
    alignSelf: "center",
  },
  buttonContainer: {
    flex: 1,
    justifyContent: "center",
  },
  buttonRow: {
    flexDirection: "row",
    justifyContent: "space-around",
    marginHorizontal: 20,
    marginBottom: 20,
  },
  button3: {
    backgroundColor: "#E9C46A",
    width: 150,
    height: 50,
    paddingVertical: 5,
    paddingHorizontal: 5,
    borderRadius: 5,
  },
  button4: {
    backgroundColor: "#2A9D8F",
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
  button1: {
    backgroundColor: "#F4A261",
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
  button5: {
    backgroundColor: "#96d5d8",
    width: 150,
    height: 50,
    paddingVertical: 5,
    paddingHorizontal: 5,
    borderRadius: 5,
  },
  image: {
    width: 200,
    height: 200,
  },
});

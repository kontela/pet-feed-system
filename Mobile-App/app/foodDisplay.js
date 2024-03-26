import { StyleSheet, Text, View, Image } from "react-native";
import React, { useState, useEffect } from "react";
import axios from "axios";

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

const FoodDisplay = () => {
  const [foodAmount, setFoodAmount] = useState(0);

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

  return (
    <View style={styles.container}>
      <Text>Food amount is: {foodAmount} gram(s)</Text>
    </View>
  );
};

export default FoodDisplay;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  image: {
    width: 200,
    height: 200,
  },
});

import { StyleSheet, Text, View, FlatList } from "react-native";
import React, { useState, useEffect } from "react";

const fetchHistory = async () => {
  const result = { data: [], error: null, loading: true };

  try {
    const response = await fetch(
      "https://jsonplaceholder.typicode.com/users/1/todos" // https://jsonplaceholder.typicode.com/users/1/todos
    );
    const data = await response.json();

    result.data = data;
  } catch (error) {
    result.error = error;
  } finally {
    loading = false;
  }

  return result;
};

const history = () => {
  const [itemList, setItemList] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await fetchHistory();
      setItemList(result.data);
    };
    fetchData();
  }, []);

  return (
    <View>
      <Text>List of Items:</Text>

      {itemList.loading ? (
        <Text>Loading...</Text>
      ) : itemList.error ? (
        <Text style={{ alignSelf: "center" }}>Error: {error.message}</Text>
      ) : (
        <FlatList
          data={itemList}
          keyExtractor={(item) => item.title}
          renderItem={({ item }) => (
            <View>
              <Text>
                {item.completed ? "Success" : "Failure"} : {item.title}
              </Text>
            </View>
          )}
        />
      )}
    </View>
  );
};

export default history;

const styles = StyleSheet.create({});

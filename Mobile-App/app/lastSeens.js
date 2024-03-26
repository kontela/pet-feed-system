import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View, FlatList, Image } from "react-native";
import axios from "axios";

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

const LastSeens = () => {
  const [imageData, setImageData] = useState({});
  const [list, setList] = useState([]);

  useEffect(() => {
    const fetchD = async () => {
      const l = await fetchPhotolist();
      setList(l);
    };
    fetchD();
  }, []);
  useEffect(() => {
    const fetchImageData = async (id) => {
      const data = await fetchSinglePhoto(id);
      setImageData((prevData) => ({ ...prevData, [id]: data }));
    };

    list.forEach((item) => {
      fetchImageData(item.id);
    });
  }, [list]);

  return (
    <View>
      <FlatList
        data={list}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={{ alignSelf: "center" }}>
            <Text>Last Seen: {item.timeOfDay}</Text>
            {imageData[item.id] && (
              <Image
                source={{ uri: `data:image/jpg;base64,${imageData[item.id]}` }}
                style={styles.image}
              />
            )}
          </View>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  image: {
    width: 100,
    height: 100,
  },
});

export default LastSeens;

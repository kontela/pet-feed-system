import { Stack } from "expo-router";

export default function Layout() {
  return (
    <Stack
      screenOptions={{
        headerStyle: {
          backgroundColor: "#264653",
        },
        headerTintColor: "white",
        headerTitleStyle: {
          fontWeight: "bold",
        },
        contentStyle: {
          backgroundColor: "#F4F4F9",
        },
      }}
    />
  );
}
{
  /*       <Stack.Screen name="home" />
      <Stack.Screen name="history" />
      <Stack.Screen name="schedule" /> */
}

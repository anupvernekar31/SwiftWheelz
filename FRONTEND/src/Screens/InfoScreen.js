import React, { useState, useEffect } from "react";
import {
  Image,
  SafeAreaView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  ActivityIndicator,
} from "react-native";
import { AntDesign } from "@expo/vector-icons";
import { MaterialIcons } from "@expo/vector-icons";
import { useDispatch, useSelector } from "react-redux";
import {
  deleteCar,
  deleteCarSuccess,
  updateCar,
} from "../Redux/carSlice/carSlice";

const back = require("../Assets/icons/left-arrow.png");
const dots = require("../Assets/icons/dots.png");

const InfoScreen = ({ route, navigation }) => {
  const vehicle = route.params.car;
  const userId = route.params.userId;
  const dispatch = useDispatch();
  const cars = useSelector((state) => state.cars.cars);
  const isDeleting = useSelector((state) => state.cars.isDeleting);
  const [refreshedCars, setRefreshedCars] = useState([]);
  const { isAdmin } = route.params;

  const displayedCar = cars.find((item) => item.id === vehicle.id);

  const handleDelete = (id) => {
    setTimeout(() => {
      dispatch(deleteCar(id));
    }, 1000);

    navigation.navigate("Initial");

    setTimeout(() => {
      dispatch(deleteCarSuccess());
    }, 1000);
  };

  return (
    <SafeAreaView style={styles.safeArea}>
      <View style={styles.container}>
        <View style={styles.headerSection}>
          <TouchableOpacity
            onPress={() => navigation.navigate("Initial")}
            activeOpacity={0.9}
          >
            <Image
              source={back}
              resizeMode="contain"
              style={styles.menuIconStyle}
            />
          </TouchableOpacity>

          <View style={{ flexDirection: "row", gap: 20 }}>
            <TouchableOpacity
              style={{
                zIndex: 1,
                paddingTop: 3,
                paddingLeft: 20,
                marginBottom: -10,
                height: 30,
              }}
            >
              {true ? (
                <AntDesign name="heart" size={24} color="black" />
              ) : (
                <AntDesign name="hearto" size={24} color="black" />
              )}
            </TouchableOpacity>

            {isAdmin && (
              <TouchableOpacity
                disabled={isDeleting}
                onPress={() => handleDelete(vehicle.id)}
                activeOpacity={0.9}
              >
                {!isDeleting ? (
                  <MaterialIcons name="delete" size={30} color="black" />
                ) : (
                  <ActivityIndicator size={40} color="black" />
                )}
              </TouchableOpacity>
            )}
          </View>
        </View>

        <View style={styles.imageSection}>
          <Image
            source={{ uri: vehicle.image }}
            resizeMode="contain"
            style={styles.vehicleImage}
          />
        </View>

        <View style={styles.headSection}>
          <View style={styles.topTextArea}>
            <Text style={styles.makemodelText}>
              {vehicle.brand} {vehicle.name}
            </Text>
            <Text style={styles.price}>
              <Text style={styles.amount}>${vehicle.price}</Text> /day
            </Text>
          </View>
          <Text style={styles.typetranText}>
            {vehicle.type}-{vehicle.transmission}
          </Text>
        </View>

        <Text style={styles.descriptionText}>
          {
            "Discover the PEUGEOT SUV 3008 with its unique design with innovative SUV codes. Its characteristic and original design combines power and elegance with dynamism and flowing lines."
          }
        </Text>
        <Text style={styles.propertiesText}>Properties</Text>

        <View style={styles.propertiesArea}>
          <View style={styles.level}>
            <Text style={styles.propertyText}>
              Motor power:
              <Text style={styles.valueText}> {"120"} hp</Text>
            </Text>
            <Text style={styles.propertyText}>
              Engine capacity:
              <Text style={styles.valueText}> {"1560"} cc</Text>
            </Text>
          </View>
          <View style={styles.level}>
            <Text style={styles.propertyText}>
              Fuel:
              <Text style={styles.valueText}> {"Diesel"}</Text>
            </Text>

            <Text style={styles.propertyText}>
              Traction:
              <Text style={styles.valueText}> {"4x2"}</Text>
            </Text>
          </View>
        </View>

        <TouchableOpacity
          onPress={() => {
            // navigation.goBack();
            !displayedCar.booked
              ? navigation.navigate("Book", { car: vehicle, userId })
              : null;
          }}
          style={styles.rentButton}
        >
          <Text style={styles.rentButtonText}>
            {displayedCar.booked ? "Booked " : "Rent a Car"}
          </Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
};

export default InfoScreen;

const styles = StyleSheet.create({
  safeArea: {
    flex: 1,
    backgroundColor: "white",
  },
  container: {
    flex: 1,
    paddingRight: 35,
    paddingLeft: 35,
    paddingTop: 30,
  },
  headerSection: {
    height: 50,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  menuIconStyle: {
    width: 25,
  },
  HeaderText: {
    fontSize: 20,
    marginLeft: 5,
    fontWeight: "500",
  },
  faceIconStyle: {
    width: 30,
  },

  imageSection: {
    width: "100%",
    justifyContent: "center",
    alignItems: "center",
  },
  vehicleImage: {
    width: 300,
    height: 300,
  },

  headSection: {},
  topTextArea: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  makemodelText: {
    fontSize: 20,
    fontWeight: "500",
  },
  price: {
    fontWeight: "400",
  },
  amount: {
    fontWeight: "bold",
  },
  typetranText: {
    marginTop: 1,
    color: "#696969",
    fontWeight: "600",
    fontSize: 12,
  },
  descriptionText: {
    marginTop: 30,
    fontSize: 14,
    letterSpacing: 0.1,
    lineHeight: 18,
    color: "#696969",
    fontWeight: "500",
  },
  propertiesText: {
    marginTop: 20,
    fontSize: 19,
    fontWeight: "500",
  },
  propertiesArea: {
    marginTop: 20,
    flexDirection: "row",
    justifyContent: "flex-start",
  },
  level: {
    marginRight: 30,
  },
  propertyText: {
    fontSize: 12,
    color: "#696969",
  },
  valueText: {
    fontSize: 12,
    color: "black",
  },
  rentButton: {
    marginTop: 50,
    height: 40,
    // padding: 10,
    alignSelf: "center",
    width: 250,
    backgroundColor: "black",
    borderRadius: 8,
    justifyContent: "center",
    alignItems: "center",
  },
  rentButtonText: {
    color: "white",
    fontWeight: "500",
  },
});

package ru.nshi.lesson3.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nshi.lesson3.jackson.models.Car;

public class Main {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Car car = new Car();

        String serializedCar = serialize(car);
        System.out.println("serializedCar = " + serializedCar);

        Car deserializedCar = deserialize(serializedCar);
        System.out.println("deserializedCar = " + deserializedCar);

        String rawString = "{\n" +
            "  \"car_model_name\" : \"Lada\",\n" +
            "  \"maxSpeed\" : 100,\n" +
            "  \"minSpeed\" : 1\n" +
            "}";

        Car deserializedCarWithUnknownField = deserialize(rawString);
        System.out.println(deserializedCarWithUnknownField);

        catchErrorExample();
    }

    public static String serialize(Car car) throws JsonProcessingException {
        return mapper
//            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(car);
    }

    public static Car deserialize(String json) throws JsonProcessingException {
        return mapper.readValue(json, Car.class);
    }

    public static void catchErrorExample() {
        int[] array = null;
        try {
            array[0] = 0;
        } catch (Exception ex) {
            System.out.println("Catch error: " + ex.getMessage());
        }
    }
}

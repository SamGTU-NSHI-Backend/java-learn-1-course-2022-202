package ru.nshi.lesson3.jackson.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder(value = {"modelName", "maxSpeed"})
public class Car {
    @JsonProperty("carModelName")
    @JsonAlias({"car_model_name", "model_name"})
    private String modelName;
    private int maxSpeed;

    @JsonIgnore
    private int year;

    public Car() {
        this("Lada", 100, 2000);
    }

    public Car(String modelName, int maxSpeed, int year) {
        this.modelName = modelName;
        this.maxSpeed = maxSpeed;
        this.year = year;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
            "modelName='" + modelName + '\'' +
            ", maxSpeed=" + maxSpeed +
            '}';
    }
}

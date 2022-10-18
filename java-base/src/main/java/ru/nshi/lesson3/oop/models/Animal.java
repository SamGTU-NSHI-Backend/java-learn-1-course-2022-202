package ru.nshi.lesson3.oop.models;

public interface Animal {

    String CONSTANT = "DGGF";

    String getName();

    default int getAge() {
        return Integer.MAX_VALUE;
    }
}

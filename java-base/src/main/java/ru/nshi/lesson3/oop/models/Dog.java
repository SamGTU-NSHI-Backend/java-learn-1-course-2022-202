package ru.nshi.lesson3.oop.models;

public class Dog implements Animal, Runnable {
    public static final String DEFAULT_NAME = "default";
    private int age;
    private String name;
    private boolean male;

    public Dog() {
        this(DEFAULT_NAME, 10);
    }

    public Dog(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public static void echo(String text) {
        System.out.println(text);
    }

    @Override
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public void bark() {
        for (int i = 0; i < 3; i++) {
            singleBark();
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
            "age=" + age +
            ", name='" + name + "'" +
            ", male=" + male +
            '}';
    }

    private void singleBark() {
        System.out.println("The dog with name " + name +
            " is barking!");
    }
}

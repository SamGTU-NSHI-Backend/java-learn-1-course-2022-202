package ru.nshi.lesson3.oop;

import ru.nshi.lesson3.oop.models.Animal;
import ru.nshi.lesson3.oop.models.Dog;
import ru.nshi.lesson3.oop.models.Rat;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();
        System.out.println("animal.getName() = " + animal.getName());
        System.out.println("animal.getAge() = " + animal.getAge());
        System.out.println("animal = " + animal);

        Dog dog = new Dog();
        System.out.println("dog = " + dog);
        dog.setName("Sharik");
        System.out.println("dog = " + dog);

        Dog.echo("Echo message");


        Animal[] animals = new Animal[]{
            new Dog(), new Rat(), new Dog("Sharik", 10)
        };

        System.out.println("\nAnimals list:");
        for (Animal anim : animals) {
            System.out.println(anim);
        }
    }
}

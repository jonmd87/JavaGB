package ru.gb.gerasimenko;

import ru.gb.gerasimenko.Fruits.Apple;
import ru.gb.gerasimenko.Fruits.Box;
import ru.gb.gerasimenko.Fruits.Fruit;
import ru.gb.gerasimenko.Fruits.Orange;

public class Task3 {
    public static void main(String[] args) {
        Task3 task3 = new Task3();

        Box<Apple> appleBox = Box.create();
        task3.addFruitsInBox(appleBox, 6, new Apple());

        Box<Orange> orangeBox = Box.create();
        task3.addFruitsInBox(orangeBox, 4, new Orange());

        System.out.println("Weight of the \"appleBox\": " + appleBox.getWeight());
        System.out.println("Weight of the \"orangeBox\": " + orangeBox.getWeight());
        System.out.printf("the weight in both boxes is %sthe same\n\n", orangeBox.compare(appleBox) ? "" : "not ");


        Box<Apple> secondAppleBox = Box.create();
        Box<Orange> secondOrangeBox = Box.create();


        appleBox.pourToAnotherBox(secondAppleBox);
        orangeBox.pourToAnotherBox(secondOrangeBox);
        System.out.println("I put one orange in a box of oranges -->|" + secondOrangeBox.addToBox(new Orange()));

        System.out.println("\nWeight of the \"appleBox\": " + appleBox.getWeight());
        System.out.println("Weight of the \"orangeBox\": " + orangeBox.getWeight());
        System.out.println("Weight of the \"secondAppleBox\": " + secondAppleBox.getWeight());
        System.out.println("Weight of the \"secondOrangeBox\": " + secondOrangeBox.getWeight());
        System.out.printf("the weight in both boxes is %sthe same\n", secondOrangeBox.compare(secondAppleBox) ? "" : "not ");

        System.out.println("check if can pour from empty box");
        Box<Apple> emptyBox = Box.create();
        emptyBox.pourToAnotherBox(secondAppleBox);
    }

    public<T extends Fruit> void addFruitsInBox(Box<T> box, int value, T fruit) {
        for (int i = 0; i < value; i++) {
            box.addToBox(fruit);
        }
    }
}

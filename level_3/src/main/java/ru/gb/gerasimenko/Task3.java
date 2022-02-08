package ru.gb.gerasimenko;

/*
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
    (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
    которую подадут в compare в качестве параметра, true - если их веса равны,
    false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
    (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
     соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
 */

import ru.gb.gerasimenko.Fruits.Apple;
import ru.gb.gerasimenko.Fruits.Box;
import ru.gb.gerasimenko.Fruits.Fruit;
import ru.gb.gerasimenko.Fruits.Orange;

import java.util.ArrayList;

public class Task3 {
    public static void main(String[] args) {
        Task3 task3 = new Task3();

        Box<Apple> appleBox = Box.create();
        task3.addFruitsInBox(appleBox, 6, new Apple());

        Box<Orange> orangeBox = Box.create();
        task3.addFruitsInBox(orangeBox, 4, new Orange());



        System.out.println("Weight of the box with  appleBox: " + appleBox.getWeight());
        System.out.println("Weight of the box with orangeBox: " + orangeBox.getWeight());
        System.out.printf("the weight in both boxes is %sthe same\n\n", orangeBox.compare(appleBox) ? "" : "not ");


        Box<Apple> secondAppleBox = Box.create();
        Box<Orange> secondOrangeBox = Box.create();


        System.out.println("Add another orange");
        secondOrangeBox.addToBox(new Orange());

        System.out.println("\nWeight of the box with  appleBox: " + appleBox.getWeight());
        System.out.println("Weight of the box with orangeBox: " + orangeBox.getWeight());
        System.out.println("Weight of the box with  secondAppleBox: " + secondAppleBox.getWeight());
        System.out.println("Weight of the box with secondOrangeBox: " + secondOrangeBox.getWeight());
        System.out.printf("the weight in both boxes is %sthe same\n", secondOrangeBox.compare(secondAppleBox) ? "" : "not ");
    }

    public<T extends Fruit> void addFruitsInBox(Box<T> box, int value, T fruit) {
        for (int i = 0; i < value; i++) {
            box.addToBox(fruit);
        }
    }

    // если использовать этот метод для пересыпания то возможно пересыпать из applebox в orangebox
    public<T extends Fruit> void pourFruits(ArrayList<? super T> destination, ArrayList<? extends T> source) {
        for (T t : source) {
            t.
        }
    }
}

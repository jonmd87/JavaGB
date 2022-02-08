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
        System.out.printf("the weight in both boxes is %sthe same\n", orangeBox.compare(appleBox) ? "" : "not ");

        for (int i = 0; i < appleBox.getList().size(); i++) {
            Apple a = (Apple) appleBox.getList().get(i);
            System.out.println(a.getName());
        }

        for (int i = 0; i < orangeBox.getList().size(); i++) {
            Orange o = (Orange) orangeBox.getList().get(i);
            System.out.println(o.getName());
        }

        Box<Apple> appleBox1 = Box.create();
        Box<Orange> orangeBox1 = Box.create();

//        task3.pourFruits(appleBox1.getList(), appleBox.getList());
//        task3.pourFruits(orangeBox1.getList(), orangeBox.getList());
        orangeBox1.addToBox(new Orange());

        System.out.println("Weight of the box with  appleBox: " + appleBox.getWeight());
        System.out.println("Weight of the box with orangeBox: " + orangeBox.getWeight());
        System.out.println("Weight of the box with  appleBox1: " + appleBox1.getWeight());
        System.out.println("Weight of the box with orangeBox1: " + orangeBox1.getWeight());
        System.out.printf("the weight in both boxes is %sthe same\n", orangeBox1.compare(appleBox1) ? "" : "not ");
    }

    public<T extends Fruit> void addFruitsInBox(Box<T> box, int value, T fruit) {
        for (int i = 0; i < value; i++) {
            box.addToBox(fruit);
        }
    }

    public<T extends Fruit> void pourFruits(ArrayList<? super Fruit> destination, ArrayList<? extends Fruit> source) {
        for (Fruit fruit : source) {
            
        }
    }
}

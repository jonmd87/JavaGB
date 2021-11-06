package ru.gb.gerasimenko;

import java.util.Random;

public class HomeWorkLessSeven
{
    public static void main( String[] args ) {
        Random random = new Random();
        String catName = "Cat";
        Plate plate1 = new Plate(100);
        Cat[] cats = new Cat[10];
        int stdApetite = 20;
        for (int i = 0; i < cats.length; i++) {
            int apetite = random.nextInt(stdApetite + i); // создаем рандомный аппетит
            cats[i] = new Cat(catName + i, apetite); // создаем кота
        }
        printCatsSatiety(cats);
    }

    public void feedCats(Cat[] cats, Plate plate) {
        int ind = 0;
        while (ind < cats.length && plate.getFoodQuantity() > 0) {
            // здесь надо продумать как кормить кота и опустошать тарелку
        }
    }

    public static void printCatsSatiety(Cat[] cats) {
        System.out.printf("%-10s %10s %10s %20s\n" , "NAME", "APETITE", "SATIETY", "CONCLUSION");
        for (Cat c : cats) {
            System.out.printf("%-10s %10d %10b ", c.getName(), c.getAppetite(), c.getSatiety());
            System.out.printf("%20s\n", (c.getSatiety() ? " is full." : " is hangry."));
        }
    }
}


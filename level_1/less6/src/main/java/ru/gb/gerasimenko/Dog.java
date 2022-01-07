package ru.gb.gerasimenko;

public class Dog extends Animal {
    private static long dogsMmbers = 0;

    public static void main(String[] args) {}

    public Dog (String name, int run, int sweem) {
        super(name, run, sweem);
        dogsMmbers++;
    }

    public static long getMembers() {return dogsMmbers;}
}

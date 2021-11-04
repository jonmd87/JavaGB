package ru.gb.gerasimenko;

public class Cat extends Animal{
    private static long catsMembers = 0;
    public static void main(String[] args) {

    }
    public Cat (String name, int run) {
        super(name, run, 0);
        catsMembers++;
    }

    public static long getMembers() {
        return catsMembers;
    }

}

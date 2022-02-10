package ru.gb.gerasimenko.Fruits;

public abstract class Fruit {
    private double weight;
    private String name;

    public Fruit(double weight) {
        this.weight = weight;
    }

    public  double getWeight(){
        return this.weight;
    };

    public String getName() {
        return name;
    }
}

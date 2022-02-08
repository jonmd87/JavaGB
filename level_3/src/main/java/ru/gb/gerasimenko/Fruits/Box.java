package ru.gb.gerasimenko.Fruits;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> list;

    private Box() {
        this.list = new ArrayList<T>();
    }

    public double getWeight() {
        double weigth = 0;
        if (list.size() > 0) {
            for (T temp : list) {
                weigth += temp.getWeight();
            }
        }
        return weigth;
    }

    public boolean addToBox(T fruit) {
        return list.add(fruit);
    }

    public static<A extends Fruit> Box<A> create() {
        return new Box<A>();
    }

    public<T extends Fruit> boolean compare(Box<T> box) {
        double epsilon = 0.000001d;
        return (Math.abs(this.getWeight() - box.getWeight()) < epsilon);
    }

    public <T> ArrayList<T> getList() {
        return (ArrayList<T>) this.list;
    }
}

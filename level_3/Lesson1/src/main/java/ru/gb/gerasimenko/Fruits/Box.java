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

    public T pullOneOut() {
        T fruit = null;
        int last = this.list.size() - 1;
        if (last >= 0) {
            fruit = this.list.get(last);
            this.list.remove(last);
        }
        return fruit;
    }

//    f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
//            (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
//    соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
    public void pourToAnotherBox(Box<T> secondBox) {
        while (this.list.size() > 0) {
            secondBox.addToBox(this.pullOneOut());
        }
    }
}

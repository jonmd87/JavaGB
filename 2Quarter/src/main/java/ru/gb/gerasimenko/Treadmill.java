package ru.gb.gerasimenko;

public class Treadmill {
    private static String entity = "Treadmill";

    private int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    public static String getEntity() {
        return entity;
    }

    public String toString() {
        return "[ " + getEntity() + "_" + distance + " metrs. ]";
    }
}

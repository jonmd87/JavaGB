package ru.gb.gerasimenko;

public class Wall {
    private static String entity = "Wall";

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public static String getEntity() {
        return entity;
    }

    public String toString() {
        return "[ " + getEntity() + "_" + height + " metrs. ]";
    }
}

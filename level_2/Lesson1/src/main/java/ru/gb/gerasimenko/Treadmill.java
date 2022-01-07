package ru.gb.gerasimenko;

public class Treadmill extends Obstruction{
    private static final String entity = "Treadmill";

    private String name;
    private final int value;

    @Override
    public boolean passObstruction(int value) {
        return value >= this.value ? true : false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", value=" + value +
                ']';
    }

    public Treadmill(int distance) {
        this.value = distance;
        this.name = Treadmill.entity + "_" + this.value;
    }

}

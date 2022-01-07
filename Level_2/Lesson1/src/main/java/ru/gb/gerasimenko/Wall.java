package ru.gb.gerasimenko;

public class Wall extends Obstruction{
    private static final String entity = "Wall";

    private String name;
    private int value;


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

    public Wall(int value) {
        this.value = value;
        this.name = Wall.entity + "_" + this.value;
    }
}

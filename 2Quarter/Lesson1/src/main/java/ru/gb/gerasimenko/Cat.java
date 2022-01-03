package ru.gb.gerasimenko;

public class Cat implements Running, Jumping{
    private static int catsAmount = 0;
    private static String entity = "Cat";

    private String name;
    private int maxRun;
    private int maxJump;
    private boolean active;

    @Override
    public void jump() {
        System.out.println(this.name + "is jumping");
    }
    @Override
    public void run() {
        System.out.println(this.name + " is runnig");
    }

    public Cat(){
        Cat.catsAmount++;
        this.name = entity + catsAmount;
        this.active = true;
    }

    public static int getCatsAmount() {
        return catsAmount;
    }

    public String getEntity() {
        return entity;
    }

    public String getName() {
        return name;
    }

    public int getMaxRun() {
        return maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }

    public String toString() {
        return this.name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

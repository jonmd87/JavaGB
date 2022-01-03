package ru.gb.gerasimenko;

public class Human implements Running, Jumping{
    private static int humansAmount = 0;
    private static String entity = "Human";

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

    public Human(){
        Human.humansAmount++;
        this.name = entity + humansAmount;
        this.active = true;
    }

    public static int getHumansAmount() {
        return humansAmount;
    }

    public static String getEntity() {
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

    public void setActive(boolean active) {
        this.active = active;
    }
}

package ru.gb.gerasimenko;

public class Robot implements Running, Jumping{
    private static int robotsAmount = 0;
    private static String entity = "Robot";

    private String name;
    private int maxRun = 100;
    private int maxJump = 1;
    private boolean active;

    @Override
    public void jump() {
        System.out.println(this.name + "is jumping");
    }
    @Override
    public void run() {
        System.out.println(this.name + " is runnig");
    }

    public Robot(){
        Robot.robotsAmount++;
        this.name = entity + robotsAmount;
        this.active = true;
    }

    public String getEntity() {
        return entity;
    }

    public String getName() {
        return name;
    }

    public static int getRobotsAmount() {
        return robotsAmount;
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

package ru.gb.gerasimenko;


public class Human implements Actions{
    private static int humansAmount = 0;
    private static String entity = "Human";

    private String name;
    private int maxRun;
    private int maxJump;
    private boolean active;

    public Human(int maxRun, int maxJump){
        Human.humansAmount++;
        this.name = Human.entity + Human.humansAmount;
        this.active = true;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public void setActive(boolean flag) {
        this.active = flag;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMaxRun() {
        return this.maxRun;
    }

    @Override
    public int getMaxJump() {
        return this.maxJump;
    }

    @Override
    public void jump() {
        System.out.println(this.name + " is jumping");
    }

    @Override
    public void run() {
        System.out.println(this.name + " is runnig");
    }

    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", maxRun=" + maxRun +
                ", maxJump=" + maxJump +
                ", active=" + active +
                ']';
    }
}

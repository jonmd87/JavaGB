package ru.gb.gerasimenko;

public abstract class Animal
{
    private String name;
    private int run;
    private int sweem;
    private static long members = 0;

    public Animal(){
        this("null", 0, 0);
    }

    public Animal(String name, int run, int sweem) {
        this.name = name;
        this.run = run;
        this.sweem = sweem;
        members++;
    }

    public String getName() {return this.name;}
    public int getRun() {return this.run;}
    public int getSweem() {return this.sweem;}

    public  void run(int distance) {
        if (distance < 0) {distance = 0;}
        if (distance > this.run) {
            System.out.print(this.name + " пробежал " + this.run + " метров, ");
            System.out.println("оставшиеся " + (distance - this.run) + " метров он прошел.");
        } else {
            System.out.println(this.name + " пробежал " + distance + " метров. ");
        }
    }

    public void sweem(int distance) {
        if (this.sweem == 0) {
            System.out.println(this.name + " не умеет плавать!!!");
            return ;
        }
        if (distance < 0) {distance = 0;}
        if (distance > this.sweem) {
            System.out.println(this.name + " не проплывет столько. Не будем мучать животное.");
        } else {
            System.out.println(this.name + " проплыл " + distance + " метров.");
        }
    }

    public static long getMembers() {
        return members;
    }
}

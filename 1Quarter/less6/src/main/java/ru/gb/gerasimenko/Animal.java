package ru.gb.gerasimenko;

public class Animal
{
    private double  run;
    private double  sweem;
    private int     counter;

    public static void main( String[] args ) {

    }

    public Animal(){
        this.run = 0;
        this.sweem = 0;
        counter++;
    }

    public Animal(double run, double sweem) {
        this.run = run;
        this.sweem = sweem;
        counter++;
    }
}

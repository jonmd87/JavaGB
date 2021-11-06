package ru.gb.gerasimenko;

public class Cat {
    private int stdAppetite = 10;
    private String name;
    private int appetite;
    private boolean satiety; // сытость

    public static void main(String[] args) {}

    public Cat(String name) {
        this(name, 0);
    }

/*
** Конструктор создает голодного кота даже если внести 0(ноль)
*/
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite > 0 ? appetite : stdAppetite;
    }

/*
** Метод void setAppetite(int appetite) устанавливает уровень аппетита конкретного кота
** если передаваемый аппетит больше 0(ноль), то метод устанавливает уровень аппетита и
** меняет значение сытости(satiety) на FALSE
** иначе ничего не меняется.
 */
    public void setAppetite(int appetite) {
        this.appetite = appetite > 0 ? appetite : 0;
        this.setSatiety();
    }

/*
** Метод PRIVATE void setSatiety() устанвливает является ли конкретный кот голодным,
** если уровень аппетита равен 0(ноль) то сытость равна TRUE
** иначе сытость равна FALSE
** !!! Почему метод PRIVATE void setSatiety() приватный, потому что только кот решает
** !!! голодный он или нет, никто из вне не решит за него)))
 */
    private void setSatiety() {
        this.satiety = this.appetite == 0 ? true : false;
    }

    public String getName() {return this.name;}
    public int getAppetite() {return this.appetite;}
    public boolean getSatiety() {return this.satiety;}
}

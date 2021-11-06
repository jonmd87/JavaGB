package ru.gb.gerasimenko;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety; // сытость

    public static void main(String[] args) {}

    public Cat(String name) {
        this(name, 0);
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.setAppetite(appetite);
    }

/*
** Метод void setAppetite(int appetite) устанавливает уровень аппетита конкретного кота
** если передаваемый аппетит больше 0(ноль), то метод устанавливает уровень аппетита и
** меняет значение сытости(satiety) на FALSE
** иначе ничего не меняется.
 */
    public void setAppetite(int appetite) {
        if (appetite > 0) {
            this.appetite = appetite;
            this.setSatiety();
        }
    }


/*
** Метод PRIVATE void setSatiety() устанвливает является ли конкретный кот голодным,
** если уровень аппетита равен 0(ноль) то сытость равна TRUE
** иначе сытость равна FALSE
** !!! Почему метод PRIVATE void setSatiety() приватный, потому что только кот решает
** !!! голодный он или нет, никто из вне не решит за него)))
 */
    private void setSatiety() {
        if (this.appetite == 0) {
            this.satiety = true;
        } else {
            this.satiety = false;
        }
    }

    public String getName() {return this.name;}
    public int getAppetite() {return this.appetite;}
    public boolean getSatiety() {return this.satiety;}
}

package ru.gb.gerasimenko;

public class Plate {
    private int foodQuantity;

    public static void main(String[] args) {}

    public Plate() {this(0);}

    public Plate(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        if (foodQuantity > 0) {
            this.foodQuantity = foodQuantity;
        } else {
            System.out.println("!!!Only positive numbers!!!");
        }
    }

    public int getFoodQuantity() {return (this.foodQuantity);}

/*
** Метод boolean decreaseFoodQuantity(int amount) уменьшает
** общего колоичества еды(foodQuantity) в тарелке на количество(amount).
**
** Метод вначале определяет какое количество еды останентся в данной тарелке
** если количество еды больше или равно 0(ноль), то выполняется вычитание и возвращает TRUE
** иначе возвращает FALSE
*/
    public boolean decreaseFoodQuantity(int amount) {
        if ((this.foodQuantity - amount) > -1) {
            this.foodQuantity -= amount;
            return true;
        } else {
            return false;
        }
    }
}

package ru.gb.gerasimenko;

public class Plate {
    private int foodQuantity;

    public static void main(String[] args) {}

    public Plate() {
        this.foodQuantity = 0;
    }

    public Plate(int foodQuantity) {
        this.foodQuantity = makePositive(foodQuantity);
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = makePositive(foodQuantity);

    }

    public int getFoodQuantity() {return (this.foodQuantity);}

/*
** Метод boolean decreaseFoodQuantity(int amount) уменьшает
** общего колоичества еды(foodQuantity) в тарелке на количество(amount).
**
** Метод вначале определяет какое количество еды останентся в данной тарелке
** если количество еды больше или равно 0(ноль), то выполняется вычитание и возвращает TRUE
** иначе вычитание не выполняется и возвращает FALSE
*/
    public boolean decreaseFoodQuantity(int amount) {
        if ((this.foodQuantity - amount) > -1) {
            this.foodQuantity -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int food) {
        this.foodQuantity += makePositive(food);
    }

/* Метод  public static int makePositive(int number) получает число
** если число положительное возвращяем его
** иначе возвращаем 0(ноль)
**
**
*/
    public static int makePositive(int number) {
        return number > 0 ? number : 0;
    }
}

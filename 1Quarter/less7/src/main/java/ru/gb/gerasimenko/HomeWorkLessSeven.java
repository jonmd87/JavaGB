package ru.gb.gerasimenko;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class HomeWorkLessSeven
{
    private static int defaultAppetite = 30;

    public static void main( String[] args ) {
        Cat[] cats = createCatsArray(10, 30);
        Plate plate1 = new Plate(100);

        System.out.println("Hungry cats:");
        printCatsSatiety(cats);
        feedCats(cats, plate1);
        System.out.println("\nCats was feed.");
        printCatsSatiety(cats);

        int food = needFood(cats);  // вычисляем сколько нужно еще еды
        if (food > 0) {
            System.out.println("\nSome cats are hungry, let's feed these cats!\n");
            plate1.addFood(food - plate1.getFoodQuantity()); //добавляем еду (аппетит котов минус еда в тарелке)
            feedCats(cats, plate1);
            printCatsSatiety(cats);
        }

        System.out.println("\nAll cats are full!!");
        System.out.println("\tMy mission is over!!!");
        System.out.println("\t\t\t\t\tGoodbye!!!");
    }

    public static void feedCats(Cat[] cats, Plate plate) {
        if (cats == null || cats.length < 1 || plate == null) {return ;}

        for (Cat c : cats) {
            if (c == null) {continue;}
            if (plate.getFoodQuantity() >= c.getAppetite() && plate.decreaseFoodQuantity(c.getAppetite())) {
                c.setAppetite(0);
            }
        }
    }

    public static void printCatsSatiety(Cat[] cats) {
        if (cats == null || cats.length < 1) {return;}

        System.out.printf("%-10s %10s %10s %20s\n" , "NAME", "APETITE", "SATIETY", "CONCLUSION");
        for (Cat c : cats) {
            if (c == null) {continue;}
            System.out.printf("%-10s %10d %10b ", c.getName(), c.getAppetite(), c.getSatiety());
            System.out.printf("%20s\n", (c.getSatiety() ? " is full." : " is hangry."));
        }
    }

    public static Cat[] createCatsArray(int members, int maxAppetite) {
        members = members < 1 ? 0 : members;
        maxAppetite = maxAppetite > 0 ? maxAppetite : defaultAppetite;

        Cat[] cats = new Cat[members];
        if (cats == null) {return null;}

        Random random = new Random();
        String catName = "Cat";

        for (int i = 0; i < cats.length; i++) {
            int appetite = random.nextInt(maxAppetite + i); // создаем рандомный аппетит
            cats[i] = new Cat(catName + (i + 1), appetite); // создаем кота
        }
        return cats;
    }

    public static int needFood(Cat[] cats) {
        int food = 0;
        if (cats != null && cats.length > 0) {
            for (Cat c : cats) {
                if (c == null) {continue;}
                food += c.getAppetite();
            }
        }
        return food;
    }
}


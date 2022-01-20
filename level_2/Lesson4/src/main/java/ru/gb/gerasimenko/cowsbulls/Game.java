package ru.gb.gerasimenko.cowsbulls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final List<Integer> allnumbers = new ArrayList<Integer>(List.of(0,1,2,3,4,5,6,7,8,9));
    private final char[] number;


    public Game() {
        this.number =  intArrToCharArr(generateArray());
    }

    private char[] intArrToCharArr(int[] array) {
        char[] number = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            number[i] = (char) (array[i] + '0');
        }
        return number;
    }

    private int[] generateArray() {
        int[] arr = new int[4];
        Collections.shuffle(allnumbers);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = allnumbers.get(i);
        }
        return arr;
    }

    public CowsBullsCounter calculateCowsBulls(String answer) {
        int cows = 0;
        int bulls = 0;

        for (int i = 0; i < answer.length(); i++) {
            if (number[i] == answer.charAt(i)) {
                bulls++;
            } else if (answer.contains(String.valueOf(number[i]))) {
                cows++;
            }
        }
        return new CowsBullsCounter(bulls, cows);
    }

    public String getNumber() {
        return String.valueOf(number);
    }
}

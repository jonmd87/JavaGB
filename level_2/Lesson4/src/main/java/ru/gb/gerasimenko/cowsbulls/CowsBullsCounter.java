package ru.gb.gerasimenko.cowsbulls;

public class CowsBullsCounter {
    private int bulls = 0;
    private int cows = 0;

    public CowsBullsCounter(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    public int getBulls() {
        return this.bulls;
    }

    public int getCows() {
        return this.cows;
    }

    @Override
    public String toString() {
        return  Names.BULLS.getStr() + bulls +
                Names.TABULATION4.getStr() +
                Names.COWS.getStr() + cows +
                Names.NEW_STRING.getStr();
    }
}

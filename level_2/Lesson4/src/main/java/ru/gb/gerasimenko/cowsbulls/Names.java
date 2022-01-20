package ru.gb.gerasimenko.cowsbulls;

public enum Names {
    WINDOW_TITLE("Cows and Bulls"),
    WINNER_TITLE("Congratulation!!! YOU WON!!"),
    HIDDEN_NUMBER_WAS("The hidden number was: "),
    YOUR_ANSWER("Your answer: "),
    ALERT_TITLE("ALERT INFORMATION"),
    INCORRECT_DATA("Incorect data"),
    COWS("Cows: "),
    BULLS("Bulls: "),
    TABULATION4("\t"),
    EMPTY_STRING(""),
    NEW_STRING("\n");

    private String str;

    Names(String str) {
        this.str = str;
    }

    public String getStr() {
        return this.str;
    }
}

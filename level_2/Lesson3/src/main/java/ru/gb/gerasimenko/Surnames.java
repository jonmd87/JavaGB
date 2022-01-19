package ru.gb.gerasimenko;

import java.util.ArrayList;

public enum Surnames {
    F1("Djobs","+79111111111"),
    F2("Musk", "+79222222222"),
    F3("Bush", "+79333333333"),
    F4("Stark", "+79444444444"),
    F5("Vangog", "+79555555555"),
    F6("Mone", "+79666666666"),
    F7("Bezos", "+79777777777"),
    F8("Eastwood", "+79888888888"),
    F9("Carey", "+79999999999"),
    F10("Marli", "+79000000000"),
    F11("Gadot", "+790101010101"),
    F12("DiCaprio", "+79020202020"),
    F13("Ronaldo", "+79030303030"),
    F14("Messi", "+790404040404"),
    F15("McGregor", "+79050505050"),
    F16("Craig", "+790606060606");

    private String surname;
    private String number;

    private Surnames(String surname, String number) {
        this.surname = surname;
        this.number = number;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getNumber() {
        return this.number;
    }
}

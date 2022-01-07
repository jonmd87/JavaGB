package ru.gb.gerasimenko;

public class MyArraySizeException extends Exception {
    private static final String MESSAGE = "More or Less than ";

    public MyArraySizeException(Integer number) {
        super(MESSAGE + number);
    }
}

package ru.gb.gerasimenko;

public class MyArrayDataException extends Exception{
    private static final String MESSAGE = "Wrong data in arr[%d][%d] = [%s], there must be a number!!!";

    public MyArrayDataException(int frst, int scnd, String mistake) {
        super(String.format(MESSAGE, frst, scnd, mistake));
    }
}

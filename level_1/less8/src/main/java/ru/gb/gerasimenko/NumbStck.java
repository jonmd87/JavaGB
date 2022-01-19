package ru.gb.gerasimenko;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL32;

public class NumbStck {
    private static BigDecimal[] Stack;
    private static int StackLenght;

    public static void main(String[] args) {}

    public static BigDecimal[] create(int lenght) {
        if (lenght > 0) {
            StackLenght = lenght;
            Stack = new BigDecimal[lenght];
        }
        return Stack;
    }

    public static BigDecimal pop() {
        int last = findLast();
        if (last > 0) {
            BigDecimal temp = getLastElement();
            Stack[last - 1] = null;
            return temp;
        }
        return null;
    }

    public static void push(BigDecimal bigDecimal) {
        Stack[findLast()] =bigDecimal;
    }

    public static BigDecimal getLastElement() {
        int last = findLast() - 1;
        return Stack[last];
    }

    public static int findLast() {
        int i = 0;
        while (i < StackLenght && Stack[i] != null) {
            i++;
        }
        return i;
    }

    public static void printStack() {
        int i = 0;
        System.out.println("Number Stack:");
        while (i < StackLenght) {
            System.out.println((i) + ") --> [" + Stack[i] + "]");
            i++;
        }
    }

    public static void calculateTwoLast(char operation) {
        int last = findLast() - 1;
        if (last > 0) {
            int previous = last - 1;
            doMathOperation(last, previous, operation);
        }
    }

    private static void doMathOperation(int last, int previous, char operation) {
        BigDecimal temp;
        switch (operation) {
            case ('+') :
                temp = Stack[previous].add(Stack[last]);
                Stack[last] = null;
                Stack[previous] = null;
                push(temp);
                break;

            case ('-') :
                temp = Stack[previous].subtract(Stack[last], DECIMAL32);
                Stack[last] = null;
                Stack[previous] = null;
                push(temp);
                break;

            case ('*') :
                temp = Stack[previous].multiply(Stack[last], DECIMAL32);
                Stack[last] = null;
                Stack[previous] = null;
                push(temp);
                break;

            case ('/') :
                if (Stack[last].compareTo(new BigDecimal("0")) == 0) {
                    GetResult.setError(0); // 0 for error message "can'tdivede by zero"
                    break;
                }
                temp = Stack[previous].divide(Stack[last], DECIMAL32);
                Stack[last] = null;
                Stack[previous] = null;
                push(temp);
                break;

            case ('^') :
                temp = Stack[previous].pow(Stack[last].intValue(), DECIMAL32);
                Stack[last] = null;
                Stack[previous] = null;
                push(temp);
                break;

            default:
                GetResult.setError(1);
                break;

        }
    }
}

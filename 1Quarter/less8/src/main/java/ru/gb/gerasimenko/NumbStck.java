package ru.gb.gerasimenko;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL32;

public class NumbStck {
    private static BigDecimal[] stack;
    private static int stackLenght;

    public static void main(String[] args) {}

    public static BigDecimal[] create(int lenght) {
        if (lenght > 0) {
            stackLenght = lenght;
            stack = new BigDecimal[lenght];
        }
        return stack;
    }

    public static void push(BigDecimal bigDecimal) {
        stack[findLast()] =bigDecimal;
    }

    public static BigDecimal getLastElement() {
        int last = findLast() - 1;
        return stack[last];
    }

    public static int findLast() {
        int i = 0;
        while (i < stackLenght && stack[i] != null) {
            i++;
        }
        return i;
    }

    public static void printStack() {
        int i = 0;
        System.out.println("Number stack:");
        while (i < stackLenght) {
            System.out.println((i) + ") --> [" + stack[i] + "]");
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
                temp = stack[previous].add(stack[last]);
                stack[last] = null;
                stack[previous] = null;
                push(temp);
                break;

            case ('-') :
                temp = stack[previous].subtract(stack[last], DECIMAL32);
                stack[last] = null;
                stack[previous] = null;
                push(temp);
                break;

            case ('*') :
                temp = stack[previous].multiply(stack[last], DECIMAL32);
                stack[last] = null;
                stack[previous] = null;
                push(temp);
                break;

            case ('/') :
                if (stack[last].compareTo(new BigDecimal("0")) == 0) {
                    GetResult.setError(0); // 0 for error message "can'tdivede by zero"
                    break;
                }
                temp = stack[previous].divide(stack[last], DECIMAL32);
                stack[last] = null;
                stack[previous] = null;
                push(temp);
                break;

            case ('^') :
                temp = stack[previous].pow(stack[last].intValue(), DECIMAL32);
                stack[last] = null;
                stack[previous] = null;
                push(temp);
                break;

            default:
                GetResult.setError(1);
                break;

        }
    }
}

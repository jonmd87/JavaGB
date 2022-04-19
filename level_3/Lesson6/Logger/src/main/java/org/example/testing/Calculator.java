package org.example.testing;

public class Calculator {
    public Long add(Integer a, Integer b) {
        return  (long) (a + b);
    }

    public Long divide(Integer a, Integer b) {
        if (b == 0) {
            throw new RuntimeException("divide by zero");
        }
        return (long) a / b;
    }
}

package org.example;


import java.util.concurrent.atomic.AtomicInteger;

public class Atomics
    // Compare And Set https://coderlessons.com/articles/java/kak-rabotaet-cas-sravnenie-i-zamena-v-java
{
    public static void main( String[] args ) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final int i1 = atomicInteger.incrementAndGet();
        final int i2 = atomicInteger.incrementAndGet();
        final int i3 = atomicInteger.accumulateAndGet(10, (x, y) -> x * y);
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        System.out.println("i3 = " + i3);
    }
}

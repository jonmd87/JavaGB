package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class AppSynchronizedCollections {
    public static void main(String[] args) {
        final List<String> list = new CopyOnWriteArrayList<>();
        final Set<String> set = new CopyOnWriteArraySet<>();
        final Map<String, Integer> map = new ConcurrentHashMap<>();
    }
}

package ru.gb.gerasimenko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Task2 {
    public static void main(String[] args) {
        System.out.println("Задание :");
        System.out.println("2. Написать метод, который преобразует массив в ArrayList;");
        Task2 task2 = new Task2();

        Integer[] integers = new Integer[] {1, 2, 3, 4, 5};
        task2.printList(task2.arrayToList(integers), "Integer: ");

        String[] strings = new String[] {"1string", "2string", "3string", "4string", "5string"};
        task2.printList(task2.arrayToList(strings), " String: ");
    }

    public <T> ArrayList<T> arrayToList(T[] arr){
       // return (ArrayList<T>) Arrays.asList(arr);  // короткий вариант.
        ArrayList<T> list = new ArrayList<>();
        for (T temp : arr) {
            list.add(temp);
        }
        return list;
    }


    public <T> void printList(ArrayList<T> list, String message) {
        System.out.print(message);
        for (T temp : list) {
            System.out.print("[" + temp + "] ");
        }
        System.out.println("");
    }
}

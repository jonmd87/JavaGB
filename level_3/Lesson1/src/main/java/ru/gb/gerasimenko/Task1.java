package ru.gb.gerasimenko;

public class Task1 {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        System.out.println("Задание :");
        System.out.println("Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа");
//        Integer
        Integer[] integers = new Integer[] {1, 2, 3, 4, 5} ;
        task1.printArray(integers, "(Integer Array)before swap: ");
        task1.swap(integers, 0, 4);
        task1.printArray(integers, "(Integer Array) after swap: ");
//        Double
        Double[] doubles = new Double[] {1.0, 2.0, 3.0, 4.0, 5.0};
        task1.printArray(doubles, "(Double Array)before swap: ");
        task1.swap(doubles, 0, 4);
        task1.printArray(doubles, "(Double Array) after swap: ");
//        String
        String[] strings = new String[] {"111", "222", "333", "444", "555"};
        task1.printArray(strings, "(String Array)before swap: ");
        task1.swap(strings, 0, 4);
        task1.printArray(strings, "(String Array) after swap: ");
    }

    public <T> void swap(T[] arr, int src, int dst) {
        T temp = arr[src];
        arr[src] = arr[dst];
        arr[dst] = temp;
    }

    public <T> void printArray(T[] arr, String message) {
        System.out.print(message + " ");
        for (T temp : arr) {
            System.out.print("[" + temp + "] ");
        }
        System.out.println("");
    }
}

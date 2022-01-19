package ru.gb.gerasimenko.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private HashMap<String, String> phoneBook;

    PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String tel, String surname) {
        if (tel != null && surname != null) {
            this.phoneBook.put(tel, surname);
        }
    }

    public String get(String surname) {
        StringBuilder str = new StringBuilder();
        if (this.phoneBook.containsValue(surname)) {
            for (Map.Entry<String, String> temp : this.phoneBook.entrySet()) {
                if (temp.getValue().equals(surname)) {
                    str.append(surname + " " + temp.getKey() + "\n");
                }
            }
        }
        return str.toString();
    }

    public ArrayList<String> getValues() {
        return new ArrayList<String>(this.phoneBook.values());
    }

    public ArrayList<String> getKeys() {
        return new ArrayList<String>(this.phoneBook.keySet());
    }

    public void printPhoneBook() {
        System.out.println(this.phoneBook);
    }
}

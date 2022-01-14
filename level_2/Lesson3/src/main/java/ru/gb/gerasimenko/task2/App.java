package ru.gb.gerasimenko.task2;

import java.util.HashSet;
import java.util.Random;

import ru.gb.gerasimenko.Surnames;

public class App {
    private static Random random = new Random();

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();
        randomPhoneBook(phoneBook);
        phoneBook.printPhoneBook();
        phoneBook.add("null",null); //testing null
        for (String s : new HashSet<>(phoneBook.getValues())) {
            System.out.println(phoneBook.get(s));
        }
    }

    private static void randomPhoneBook(PhoneBook phoneBook) {
        int lenght = Surnames.values().length;
        for (int i = 0; i < lenght; i++) {
            String tel = Surnames.values()[i % lenght].getNumber();
            int num = random.nextInt(lenght);
            String surname = Surnames.values()[num].getSurname();
            phoneBook.add(tel, surname);
        }
    }
}

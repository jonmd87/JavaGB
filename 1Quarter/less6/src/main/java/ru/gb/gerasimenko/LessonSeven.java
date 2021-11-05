package ru.gb.gerasimenko;

public class LessonSeven {
    public static void main(String[] args) {
        Cat cat = new Cat("Tom", 200);
        Dog dog = new Dog("Charly", 500, 10);

        System.out.println("name of cat " + cat.getName());
        System.out.println("name of dog " + dog.getName());
        System.out.println("");
// проверка котов на бег
        cat.run(100);
        cat.run(220);
        cat.run(-2);
        System.out.println("");

//проверка собак на бег
        dog.run(400);
        dog.run(575);
        dog.run(-2);
        System.out.println("");

// проверка котов на плаванье
        cat.sweem(10);
        System.out.println("");

//проверка собак на плаванье
        dog.sweem(40);
        dog.sweem(5);
        dog.sweem(-2);
        System.out.println("");


        System.out.println("общее количество котов = " + Cat.getMembers());
        System.out.println("общее количество собак = " + Dog.getMembers());
        System.out.println("общее количество животных = " + Animal.getMembers());
    }


}

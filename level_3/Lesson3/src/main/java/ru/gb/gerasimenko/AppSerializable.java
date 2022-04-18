package ru.gb.gerasimenko;

import java.io.*;
import java.util.Arrays;

public class AppSerializable {


    // Сериализация
    // Десериализация
    private static class  Cat implements Serializable{
        private String name;
        private int age;
        private transient double weight; // transient = это поле не будет подверженно сериализации

        public Cat(String name, int age, double weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        byte[] byteArr = null;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                objectOutputStream.writeObject(new Cat("Tom", 2, 2.8));
                byteArr = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try ( ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArr);
              final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                Cat catNew = (Cat) objectInputStream.readObject();
            System.out.println(catNew);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

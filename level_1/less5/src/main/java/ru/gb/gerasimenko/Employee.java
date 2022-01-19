package ru.gb.gerasimenko;

public class Employee {
    private String name;
    private String post;
    private String telephon;
    private String email;
    private double salary;
    private int age;

    public static void main(String[] args) {
    }

    public void showData() {
        System.out.printf("%-20s", this.getName());
        System.out.printf("%-15s", this.getPost());
        System.out.printf("%-15s", this.getTelephon());
        System.out.printf("%-20s", this.getEmail());
        System.out.printf("%15.2f", this.getSalary());
        System.out.printf("%10d", this.getAge());
        System.out.println("");
    }

//Constructor
    public Employee(String name, String post, String telephon, String email, double salary, int age) {
        this.name = name;
        this.post = post;
        this.telephon = telephon;
        this.email = email;
        this.salary = salary;
        this.age = age;
    }

//SET*******************

    public void setName(String name) {
        this.name = name;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setTelephon(String telephon) {
        this.telephon = telephon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

//GET******************


    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getTelephon() {
        return telephon;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

}

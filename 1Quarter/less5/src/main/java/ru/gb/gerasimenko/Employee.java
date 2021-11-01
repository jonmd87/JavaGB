package ru.gb.gerasimenko;

public class Employee {
    private String name;
    private String post;
    private String telephon;
    private String email;
    private double salary;
    private int age;

    public static void main(String[] args) {
        Employee[] arrEmploys = new Employee[5];
        arrEmploys[0] = new Employee("Ivanov I.I.", "manager","+793918877", "ivanovII@gb.ru", 1000, 50);
        arrEmploys[1] = new Employee("Petrov P.P.", "finance","+796988174", "petrovPP@gb.ru", 900, 24);
        arrEmploys[2] = new Employee("Sidorov S.S.", "security","+791682800", "sidorovSS@gb.ru", 1200, 43);
        arrEmploys[3] = new Employee("Putin V.V.", "direcotor","+700000001", "PutinVV@gb.ru", 100000, 62);
        arrEmploys[4] = new Employee("Lomonosov M.V.", "scientist","+793280870", "lomonosovMV@gb.ru", 5090, 30);

        printListEmployes(arrEmploys, 40);
    }

//IDE ругался(без static), пришлось добавить
    public static void printListEmployes(Employee[] arr, int limit) {
        System.out.printf("%-20s %-15s %-15s %-20s %10s %10s\n", "NAME", "POST", "TELEPHON", "EMAIL", "SALARY", "AGE");
        for (Employee e: arr) {
            if (e.age > limit)
                e.showData();
        }
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

package ru.gb.gerasimenko;

public class Employee {
    private String name;
    private String post;
    private String telefon;
    private String email;
    private double salary;
    private int age;

    public static void main(String[] args) {
        Employee[] arrEmploys = new Employee[5];
        arrEmploys[0] = new Employee("Ivanov I.I.", "manager","+799988877", "ivanovII@gb.ru", 100000, 50);
        arrEmploys[1] = new Employee("Petrov P.P.", " finance","+799988877", "ivanovII@gb.ru", 100000, 64);
        arrEmploys[2] = new Employee("Sidorov S.S.", "security","+799988877", "ivanovII@gb.ru", 100000, 43);
        arrEmploys[3] = new Employee("Putin V.V.", "direcotor","+799988877", "ivanovII@gb.ru", 100000, 22);
        arrEmploys[4] = new Employee("Lomonosov M.V.", "scientist","+799988877", "ivanovII@gb.ru", 100000, 30);

        for (Employee e: arrEmploys) {
            e.showData();
        }
    }

    public void showData() {
        System.out.println(this.getName());
        System.out.println(this.getPost());
        System.out.println(this.getTelefon());
        System.out.println(this.getEmail());
        System.out.println(this.getSalary());
        System.out.println(this.getAge());
    }

    //Constructor
    public Employee(String name, String post, String telefon, String email, double salary, int age) {
        this.name = name;
        this.post = post;
        this.telefon = telefon;
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

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public String getTelefon() {
        return telefon;
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

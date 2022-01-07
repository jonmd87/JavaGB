package ru.gb.gerasimenko;

public class LessonSix {
    public static void main(String[] args) {
        Employee[] arrEmploys = new Employee[5];
        arrEmploys[0] = new Employee("Ivanov I.I.", "manager", "+793918877", "ivanovII@gb.ru", 1000, 50);
        arrEmploys[1] = new Employee("Petrov P.P.", "finance", "+796988174", "petrovPP@gb.ru", 900, 24);
        arrEmploys[2] = new Employee("Sidorov S.S.", "security", "+791682800", "sidorovSS@gb.ru", 1200, 43);
        arrEmploys[3] = new Employee("Putin V.V.", "direcotor", "+700000001", "PutinVV@gb.ru", 100000, 62);
        arrEmploys[4] = new Employee("Lomonosov M.V.", "scientist", "+793280870", "lomonosovMV@gb.ru", 5090, 30);

        printListEmployes(arrEmploys, 40);
    }
    //IDE ругался(без static), пришлось добавить static
    public static void printListEmployes(Employee[] arr, int limit) {
        System.out.printf("%-20s %-15s %-15s %-20s %10s %10s\n", "NAME", "POST", "TELEPHON", "EMAIL", "SALARY", "AGE");
        for (Employee e: arr) {
            if (e.getAge() > limit)
                e.showData();
        }
    }
}

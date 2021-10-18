public class SecondLesson {
    public static void main( String[] args ) {
        System.out.println("********checkLimit********");
        System.out.println("[5]  --> " + checkLimit(5));
        System.out.println("[10] --> " + checkLimit(10));
        System.out.println("[20] --> " + checkLimit(20));
        System.out.println("[25] --> " + checkLimit(25));
        System.out.println("");

        System.out.println("********possitiveOrNegative********");
        System.out.print("[5] -- > ");
        possitiveOrNegative(5);
        System.out.print("[0] -- > ");
        possitiveOrNegative(0);
        System.out.print("[-5] -- > ");
        possitiveOrNegative(-5);
        System.out.println("");

        System.out.println("********possitiveOrNegativeRetBool********");
        System.out.println("input [5]  -> " + possitiveOrNegativeRetBool(5));
        System.out.println("input [0]  -> " + possitiveOrNegativeRetBool(0));
        System.out.println("input [-5] -> " + possitiveOrNegativeRetBool(-5));
        System.out.println("");

        System.out.println("********printString(Hello GeekBrains, 3)********");
        printString("Hello GeekBrains", 3);
        System.out.println("");
        System.out.println("********printString(Hello GeekBrains, 0)********");
        printString("Hello GeekBrains", 0);
        System.out.println("********printString(Hello GeekBrains, -2)********");
    }
    //ex1
    public static boolean checkLimit(int num) {
        boolean res = true;
        if (num < 10 || num > 20) {
            res = false;
        }
        return (res);
    }
    //ex2
    public static void possitiveOrNegative (int num) {
        if (num > -1) {
            System.out.println("Положительное число");
        }
        else {
            System.out.println("Отрицательное число");
        }
    }
    //ex3
    public static boolean possitiveOrNegativeRetBool(int num) {
        boolean res = true;
        if (num > -1) {
            res = false;
        }
        return (res);
    }

    //ex4
    public static void printString (String s, int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(s);
        }
    }

    public static boolean leapYearOrNot (int year) {
        boolean res = true;
        return (res);
    }
}

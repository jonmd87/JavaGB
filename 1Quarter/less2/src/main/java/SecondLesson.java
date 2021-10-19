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
        System.out.println("");
        System.out.println("********leapYearOrNot********");
        System.out.println("2016" + (leapYearOrNot(2016) ? " высокосный.": " обычный."));
        System.out.println("1994" + (leapYearOrNot(1994) ? " высокосный.": " обычный."));
        System.out.println("1991" + (leapYearOrNot(1991) ? " высокосный.": " обычный."));
        System.out.println("1992" + (leapYearOrNot(1992) ? " высокосный.": " обычный."));
        System.out.println("1990" + (leapYearOrNot(1990) ? " высокосный.": " обычный."));
        System.out.println("2100" + (leapYearOrNot(2100) ? " высокосный.": " обычный."));
        System.out.println("1980" + (leapYearOrNot(1980) ? " высокосный.": " обычный."));



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
    ex5
    public static boolean leapYearOrNot (int year) {
        boolean res = true;
        if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
            res = false;
        }
//        if (year % 4 == 0) {
//            if (year % 100 != 0 || (year % 100 == 0 && year % 400 == 0)) {
//                res = true;
//            }
//        }
        return (res);
    }
}

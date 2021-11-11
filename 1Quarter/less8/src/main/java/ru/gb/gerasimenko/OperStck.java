package ru.gb.gerasimenko;

public class OperStck {

// индекс массива является приорететом операции
//                                      0     1     2
    private static String[] Priority = {" ", "+-", "/*", "^"};
    private static char[] Stack;
    private static int StackLenght;

    public static void main(String[] args) {}

    public static void solve(char currentOper) {
            if ((findLast() == 0) || (findLast() > 0 && priority(currentOper) > priority(getLastElement()))) {
                push(currentOper);
            } else if (findLast() > 0 && priority(currentOper) <= priority(getLastElement())) {
                while (findLast() > 0 && priority(currentOper) <= priority(getLastElement())) {
                    NumbStck.calculateTwoLast(pop());
                }
                push(currentOper);
            }
    }

    private static int priority(char ch) {
        if (ch != 0) {
            int i = 0;
            String str = Character.toString(ch);
            for (; i < Priority.length; i++) {
                if (Priority[i].contains(str)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static char[] create(int lenght) {
        if (lenght > 0) {
            StackLenght = lenght;
            Stack = new char[lenght];
        }
        return Stack;
    }

    public static char pop() {
        int last = findLast();
        if (last > 0) {
            char temp = getLastElement();
            Stack[last - 1] = 0;
            return temp;
        }
        return 0;
    }

    public static void push(char ch) {
        int last = findLast();
        Stack[last] = ch;
    }

    public static char getLastElement() {
        int last = findLast() - 1;
        if (last < 0) {
            last = 0;
        }
        return Stack[last];
    }

    public static int findLast() {
        int i = 0;
        while (i < StackLenght && Stack[i] != 0) {
            i++;
        }
        return i;
    }

    public static void printStack() {
        int i = 0;
        System.out.println("\nOperatoin Stack:");
        while (i < StackLenght) {
            System.out.println((i) + ") --> [" + Stack[i] + "]");
            i++;
        }
    }
}

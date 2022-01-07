package ru.gb.gerasimenko;

public class OperStck {

/* индекс массива является приорететом операции
**    !!!!!!!!!!!! перемести скобки на самый высший приоретет,
*/    //                                  0     1     2
    private static String[] Priority = {"()", "+-", "/*", "^"};
    private static char[] Stack;
    private static int StackLenght;

    public static void main(String[] args) {}
// 1 point --> если пустой стэк или это открывающая скобка
// 2 point --> если в стэке есть операция и ее приоретет ниже чем текущий кидаем в стэк
// 3 point --> если стэк есть операции и ее приоретет выше или равени текущему
//  3.1 point --> если это [)] пока не встретишь [(] вынимай все операции из стека. как встретишь [(] удали его из стека
//  3.2 point --> пока в стеке есть операции и ее приоретет выше или равени текущему, вынимай все операции из стека
//  3.3 point --> закинь в стек операцию currentOper

    public static void solve(char currentOper) {
            if ((findLast() == 0) || currentOper == '(') {      // 1 point
                push(currentOper);
            } else if (findLast() > 0 && priority(currentOper) > priority(getLastElement())) { // 2 point
                push(currentOper);
            } else if (findLast() > 0 && priority(currentOper) <= priority(getLastElement())) { // 3 point
                if (currentOper == ')') {
                    while (getLastElement() != '(') {NumbStck.calculateTwoLast(pop());} // 3.1 point
                    pop();
                } else {
                    while (findLast() > 0 && priority(currentOper) <= priority(getLastElement())) { //3.2 point
                        NumbStck.calculateTwoLast(pop());
                    }
                    push(currentOper); // 3.3 point
                }
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

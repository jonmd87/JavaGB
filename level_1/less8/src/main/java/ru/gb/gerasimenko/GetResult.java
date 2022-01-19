package ru.gb.gerasimenko;


import java.math.BigDecimal;

public class GetResult {
    private static String ErrorMessage;
    private static String MessageDivZero = "can't div. by 0";
    private static String MessageFormatError = "Format Error";
    private static boolean Error = false;

    private static boolean negative = false;

    public static String  getResult(String src) {
        Error = false;
        if (src.length() > 0) {
            src = ifNeedBrackets(src);
            src = polishMethod(src, src.length());
        }
        return Error ? ErrorMessage : src;
    }

    private static String ifNeedBrackets(String src) {
        int counter = 0;
        for (char ch : src.toCharArray()) {
            if (ch == CheckOperations.OpenBracket) {counter++;}
            else if (ch == CheckOperations.CloseBracket) {counter--;}
        }
        for (; counter > 0; counter--) {src += ")";}
        return src;
    }

    private static String polishMethod(String src, int length) {
        OperStck.create(length);
        NumbStck.create(length);
        for (int i = 0; i < length; ) {
            negative = false;
            i = checkIsNegative(src, i);
            if (Character.isDigit(src.charAt(i))) {
                NumbStck.push(new BigDecimal(src.substring(i, lastDigit(src, i))));
                if (negative) {NumbStck.push(NumbStck.pop().multiply(new BigDecimal("-1")));}
                for (; i < length && (Character.isDigit(src.charAt(i)) || src.charAt(i) == '.'); i++);
            }
            for (; i < length && !Character.isDigit(src.charAt(i)); i++) {
                OperStck.solve(src.charAt(i));
            }
        }
        while (OperStck.findLast() > 0) {NumbStck.calculateTwoLast(OperStck.pop());}
        src = NumbStck.getLastElement().toPlainString();
        return src;
    }

    private static int checkIsNegative(String src, int ind) {
        if (src.charAt(ind) == '-' && ind == 0){
            negative = true;
            ind += 1;
        } else if  (ind > 1 && src.charAt(ind - 1) == '-' && src.charAt(ind - 2) == '(') {
            negative = true;
            OperStck.pop();
        }
        return ind;
    }


    private static int lastDigit(String src, int start) {
        int last = 0;
        if (src == null || start < 0) {return 0;}
        for(last = start; last < src.length(); last++) {
            char ch = src.charAt(last);
            if (Character.isDigit(ch) || ch == '.') {continue;}
            break;
        }
        return last;
    }

    public static void setError(int flag) {
        if (flag == 0) {
            Error = true;
            ErrorMessage = MessageDivZero;
        } else {
            Error = true;
            ErrorMessage = MessageFormatError;
        }
    }
}

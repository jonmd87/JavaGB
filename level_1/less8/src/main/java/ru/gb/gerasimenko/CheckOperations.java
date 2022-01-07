package ru.gb.gerasimenko;

public class CheckOperations {
    public static char Point = '.';
    public static char Minus = '-';
    public static String AllOperations = "/*+^";
    public static char OpenBracket = '(';
    public static char CloseBracket = ')';

    public static String addOperation(char add, String lableText) {
        if (add == Point) {
            lableText = checkPoint(lableText);
        } else if (add == OpenBracket) {
            lableText += whichBracket(lableText);
        } else if (add == Minus) {
            lableText = checkMinus(lableText);
        } else {
            lableText = checkOperation(lableText, Character.toString(add));
        }
      return (lableText);
    }

    private static String  checkMinus(String src) {
        int ind = src.length() - 1;
        if (src.length() == 0) {src += "-";}
        else if (ind > -1 ) {
            char ch = src.charAt(ind);
            if (ch == OpenBracket || ch == CloseBracket || Character.isDigit(ch)) {src += "-";}
            else if (AllOperations.contains(Character.toString(ch))) {src += "(-";}
        }
        if (ind > 0 && src.charAt(ind) == '.') {src += "0-";}
        return src;
    }

    public static String whichBracket(String src) {
        int cnt = 0;
        boolean flag = false;
        if (src.length() > 0) {
            for (char ch : src.toCharArray()) {
                if (cnt == 0) {flag = false;}
                if (ch == OpenBracket) {cnt++;}
                else if (ch == CloseBracket) {cnt--;}
                else if (!flag && Character.isDigit(ch)) {flag = true;}
            }
        }
        return (flag && cnt > 0) ? Character.toString(CloseBracket) : Character.toString(OpenBracket);
    }

    private static String checkOperation(String src, String add) {
        int ind = src.length() - 1;
        for (;ind >= 0 && (AllOperations.contains(src.substring(ind)) || src.charAt(ind) == Minus); ind--) {
                src = src.substring(0, ind);
        }
        if (ind > 0 && src.charAt(ind) == '.') {src += "0";}
        src += (src.length() > 0 && src.charAt(ind) != OpenBracket) ? add : "";
        return src;
    }


    private static String checkPoint(String src) {
        int ind = src.length() - 1;
        String allOper = AllOperations + Character.toString(OpenBracket) + Character.toString(Minus);
        if (src.length() == 0 || (ind > -1 && allOper.contains(src.substring(ind)))) {
            src += "0.";
        } else if (CloseBracket != src.charAt(ind) && !ifDouble(src)) {src += ".";}
        return src;
    }

/*
** Метод public static boolean ifDouble(String src)
*/
        private static boolean ifDouble(String src) {
            if (src.length() > 0) {
                int i = src.length() - 1;
                for(; i != 0 && Character.isDigit(src.charAt(i)); i--);
                if (Point == src.charAt(i)) {return true;}
            }
        return false;
    }

}

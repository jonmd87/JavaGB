package ru.gb.gerasimenko;

public class HomeWork {
    public static void main(String[] args) {
        HistoryControl historyControl = new HistoryControl("ivan");
        historyControl.renameHistoryFile("john");
        String lines = historyControl.readLastLines(20);
        historyControl.log("after change");
        System.out.println(lines);
    }
}

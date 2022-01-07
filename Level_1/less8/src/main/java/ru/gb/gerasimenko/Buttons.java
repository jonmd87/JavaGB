package ru.gb.gerasimenko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Buttons {

    public static void main(String[] args) {}

    public static void changeButtonTextAndColor(String search, String replace, Color color, JButton... buttons) {
        if (search == null || replace == null || buttons == null || buttons.length == 0) {
            return;
        }
        for (JButton temp : buttons) {
            if (search.equals(temp.getText())) {
                temp.setText(replace);
                if (color != null) {
                    temp.setBackground(color);
                }
            }
        }
    }

    public static void addButtonsToPanel(JPanel panel, JButton ... buttons) {
        if (panel != null && buttons != null && buttons.length > 0) {
            for (JButton temp : buttons) {
                panel.add(temp);
            }
        }
    }

    public static JButton[] arrayButtonsFromString(String names, Color colorNumberButtons, Color colorActionButtons) {
        if (names == null || colorNumberButtons == null || colorActionButtons == null) {return null;}

        JButton[] arr = new JButton[names.length()];
        Font fontNumbers = new Font("FreeMono", Font.BOLD, 40);
        Font fontActions = new Font("Arial", Font.ROMAN_BASELINE, 30);
        for (int i = 0; i < names.length(); i++) {
            arr[i] = new JButton(names.substring(i, i + 1));
            if (Character.isDigit(names.charAt(i))) {
                arr[i].setFont(fontNumbers);
                arr[i].setBackground(colorNumberButtons);
            } else {
                arr[i].setFont(fontActions);
                arr[i].setBackground(colorActionButtons);
            }
        }
        return arr;
    }

    public static void addActionListerToButtons(ActionListener actList, JButton ... buttons) {
        if (actList == null || buttons == null || buttons.length == 0) {
            return ;
        }
        for (JButton temp : buttons) {
            if (temp != null) {
                temp.addActionListener(actList);
            }
        }
    }
}

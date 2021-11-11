package ru.gb.gerasimenko;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator
{
    private static String Title = "GERASIMENKO";
    private static String MyButtons = "D ^B789/456*123-.0=+";
    private static String Equal = "=";
    private static String Result = "result: ";
    private static String ReplaceD = "AC"; // [C] clear in MYBUTTONS
    public static String Breakets = "()"; // [(] brackets in MYBUTTONS
    public static String Backspace = "<--|"; // [B] backspace in MYBUTTONS

    private static Color OperationColor = Color.YELLOW;
    private static Color NumColor = Color.WHITE;
    private static Color SpecialColor = Color.red;
    private static Color BackspceColor = Color.PINK;

    private static int FontSizeStandart = 40;
    private static Font TextFontGreen = new Font("FreeMono", Font.BOLD, FontSizeStandart);

    public static void main( String[] args ) {

        Window calcWin = new Window(Title);
        final JLabel label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setForeground(Color.GREEN);
        label.setFont(TextFontGreen);
//create buttonsPanel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.GRAY);
//create buttons
        JButton[] arrButtons = Buttons.arrayButtonsFromString(MyButtons, NumColor, OperationColor);
        Buttons.changeButtonTextAndColor("D", ReplaceD, SpecialColor, arrButtons);
        Buttons.changeButtonTextAndColor("(", Breakets, null,arrButtons);
        Buttons.changeButtonTextAndColor("B", Backspace, BackspceColor, arrButtons);
        Buttons.changeButtonTextAndColor(" ", "", calcWin.getMainColor(), arrButtons);
//create GridLayout for buttonPanel
        GridLayout gridLForButtons = new GridLayout(5, 4, 15, 15);
        buttonsPanel.setLayout(gridLForButtons);
        Buttons.addButtonsToPanel(buttonsPanel, arrButtons);

        calcWin.add(label, BorderLayout.NORTH);
        calcWin.add(Box.createVerticalStrut(10), BorderLayout.CENTER);
        calcWin.add(buttonsPanel, BorderLayout.SOUTH);

        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             distribution(e, label);
            }
        };
        Buttons.addActionListerToButtons(actionListener, arrButtons);
        calcWin.setVisible(true);
    }

    public static void distribution(ActionEvent e, JLabel label) {
        JButton but = (JButton) e.getSource();
        Color col = but.getBackground();
        String labelText = label.getText();
        String buttonText = but.getText();

        labelText = deleteWordResult(labelText);  // delete the word "result:" from lable
        if (col.equals(SpecialColor)) { // if press button [AC]
            labelText = "";
        } else if (col.equals(BackspceColor)) {    // delete last entered charachter
            labelText = (labelText.length() > 1) ? labelText.substring(0, labelText.length() - 1) : "";
        } else if (buttonText.equals(Equal)) {   // action for button [=]
            labelText = Result + GetResult.getResult(labelText);
        } else if (col.equals(NumColor)) {      // add numbers
            labelText = addNumber(labelText, buttonText);
        } else if (col.equals(OperationColor)) {  // add operations
            labelText = CheckOperations.addOperation(buttonText.charAt(0), labelText);
        }
        label.setText(labelText);
    }

    private static String deleteWordResult(String src) {
        if (src.length() > 0 && src.contains(Result)) {
            src = src.substring(Result.length());
        }
        return src;
    }

    private static String addNumber(String src, String add) {
        int ind = src.length() - 1;
        if ((src.length() == 0) || (ind > -1 && src.charAt(ind) != ')')) { // if last charachter is ')' can't add number, need add math operation
            src += add;
        }
        return src;
    }


}

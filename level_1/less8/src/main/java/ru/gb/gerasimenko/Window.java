package ru.gb.gerasimenko;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {
    private Color MainColor = Color.DARK_GRAY;
    public static void main(String[] args){}

    public Window(String title) {
        setTitle(title);
        setWindowSize(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(MainColor);
    }
    private void setWindowSize(boolean resize) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        int width = dim.width / 4;
        int height = dim.height / 2;
        int x = dim.width / 2 - width / 2;
        int y = dim.height / 2 - height / 2;
        setBounds(x, y , width, height);
        setResizable(true);
    }

    public Color getMainColor() {
        return this.MainColor;
    }

    public void setMainColor(Color color) {
        this.setBackground(color);
    }
}

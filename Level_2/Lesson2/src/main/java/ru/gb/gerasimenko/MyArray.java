package ru.gb.gerasimenko;

public class MyArray {
    private int rows;
    private int columns;
    private String [][] array;

    public MyArray(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.array = new String[rows][columns];
    }

    public void fillArray(String filler) {
        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array[i].length; j++) {
                this.array[i][j] = filler;
            }
        }
    }

    public void showArray() {
        for (String[] arr: this.array) {
            for (String a : arr) {
                System.out.print(a + " ");
            }
            System.out.println("");
        }
    }

    public void changeElement(int row, int column, String newData) {
        if (row < 0 || column < 0 || row >= this.rows || column >= this.columns || newData == null) {
            System.out.println("Wrong input data: row or column or newData");
            return;
        }
        this.array[row][column] = null;
        this.array[row][column] = new String(newData);
    }

    public String[][] getArray() {
        return this.array;
    }
}

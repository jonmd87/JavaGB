package ru.gb.gerasimenko;


/*
    Чтоб добавить ошибку в массив необходимо у обьекта класса MyArray вызвать метод:
            changeElement(int row, int column, String newData);
    перед блоком try->catch;

 */
public class App 
{
    private static int correctLenght = 4;
    private static Integer result = null;

    public static void main( String[] args ) throws Exception {

        MyArray myArray = new MyArray(correctLenght, correctLenght); // create an Array
        myArray.fillArray("1");  // filling the array
        myArray.changeElement(1,1, "Y"); // add wrong data in array
        myArray.showArray();  // print the array
        try {
            calculateValuesOfArray(myArray.getArray());
        } catch (MyArraySizeException sizeException) {
            sizeException.printStackTrace();
        } catch (MyArrayDataException dataException) {
            dataException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (result != null) {
                System.out.println("Summ of all elements = " + result);
            }
            System.out.println("Goodbye");
        }
    }

    public static void calculateValuesOfArray(String[][] array) throws  Exception {
        if (!(checkArrayLenth(array))) {
            throw new MyArraySizeException(correctLenght);
        }
        int res = 0;
        for (int f = 0; f < array.length; f++) {
            for (int s = 0; s < array[f].length; s++) {
                try {
                    res += Integer.parseInt(array[f][s]);
                } catch (NumberFormatException numberFormatException) {
                    throw new MyArrayDataException(f, s, array[f][s]);
                }
            }
        }
        result = new Integer(res);
    }

    private static boolean checkArrayLenth(String[][] arr) {
        if (arr != null && arr.length == correctLenght) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null || arr[i].length != correctLenght) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}

import java.util.Arrays;

public class LessonThree {
    public static void main(String[] args) {
//ex1
        int[] arr1 = new int[] {1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0};
        System.out.println("Exercise 1");
        System.out.println("before change -> " + Arrays.toString(arr1));
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (arr1[i] == 0 ? 1 : 0);
        }
        System.out.println("after change --> " + Arrays.toString(arr1));
        System.out.println("");

//ex2
        System.out.println("Exercise 2");
        int max = 100;
        int[] arr2 = new int[max];
        for (int i = 0; i < max; i++) {
            arr2[i] = i + 1;
        }
        System.out.println("exercise 2 --> " + Arrays.toString(arr2));
        System.out.println("");

//ex3
        System.out.println("Exercise 3");
        int[] arr3 = new int[] {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("before --> " + Arrays.toString(arr3));
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) {
                arr3[i] *= 2;
            }
        }
        System.out.println("after  --> " + Arrays.toString(arr3));
        System.out.println("");

//ex4
        System.out.println("Exercise 4");
        max = 10;
        int[][] arr4 = new int[max][max];
        for (int i = 0; i < max; i++) {
            arr4[i][i] = 1;
            arr4[i][max - 1 - i] = 1;
        }
        for (int i = 0; i < arr4.length; i++) {
            System.out.print("line [" + i + "] --> ");
            for (int y = 0; y < arr4[i].length; y++) {
                System.out.print(arr4[i][y] + " ");
            }
            System.out.println("");
        }
        System.out.println("");

//ex5
        System.out.println("Exercise 5");
        int[] arr5 = createArray(max, max * max - max);
        System.out.println("createArray(len = 10, initialValue = 90) -return-> " + Arrays.toString(arr5));
        System.out.println("");
//ex6*
        System.out.println("Exercise 6");
        exerciseSix(max); // exercise 6
        System.out.println("");

//ex7*
        System.out.println("Exercise 7");
        int[] ar = new int[] {1, 3, 5, 4, 4, 1};
        System.out.println("( true)ar" + Arrays.toString(ar) + " --> " + checkBalance(ar));
        int[] ar1 = new int[] {1, 2, 5, 4, 4, 1};
        System.out.println("(false)ar" + Arrays.toString(ar1) + " --> " + checkBalance(ar1));
        int[] ar2 = new int[] {1, 3, 3, 3, 9, 1};
        System.out.println("( true)ar" + Arrays.toString(ar2) + " --> " + checkBalance(ar2));
        int[] ar3 = new int[] {1, 6, 4, 4, 1};
        System.out.println("(false)ar" + Arrays.toString(ar3) + " --> " + checkBalance(ar3));
        System.out.println("");

//ex8*
        System.out.println("Exercise 8");
        int[] temp = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shiftArray(temp, 1);
        shiftArray(temp, 2);
        shiftArray(temp, -2);


    }

    public static int[] createArray(int len, int initialValue) {
        int arr[] = new int[len];
        Arrays.fill(arr, initialValue);
        return (arr);
    }

//ex6
    public static void exerciseSix(int len) {
        int[] arr6 = new int[len];
        for (int i = 0; i < arr6.length; i++) {
            arr6[i] = (int)(Math.random() * len);
        }
        int min = arr6[0];
        int max = arr6[0];
        for (int i = 1; i < arr6.length; i++) {
            if (arr6[i] > max) {
                max = arr6[i];
            }
            if (arr6[i] < min) {
                min = arr6[i];
            }
        }
        System.out.println("array --> " + Arrays.toString(arr6));
        System.out.println("min = " + min);
        System.out.println("max = " + max);
    }

//ex7
    public static boolean checkBalance(int[] arr) {
        int leftSide = 0;
        int rightSide;

        for (int i = 0; i < arr.length; i++) {
            leftSide += arr[i];
            rightSide = 0;
            for (int y = i + 1; y < arr.length; y++) {
                rightSide += arr[y];
            }
            if (leftSide == rightSide) {
                return (true);
            }
        }
        return (false);
    }

//ex8*
    public static void shiftArray(int[] originalArray, int shift) {
        int num;
        if (shift < 0) {
            num = originalArray.length - Math.abs(shift);
            System.out.println("shift [" + shift + "]             << ");
        } else {
            num = shift;
            System.out.println("shift [" + shift + "]             >> ");
        }
        int[] arr = originalArray.clone();
        for (int i = 0; i < num; i++) {
            int last = arr[arr.length - 1];
            for (int y = arr.length; y - 2 >= 0; y--) {
                arr[y - 1] = arr[y - 2];
            }
            arr[0] = last;
        }
        System.out.println("before shift -->" + Arrays.toString(originalArray));
        System.out.println(" after shift -->" + Arrays.toString(arr));
    }
 }

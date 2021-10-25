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

        exerciseSix(max); // exercise 6
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
}

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

//ex2
        System.out.println("Exercise 2");
        int max = 100;
        int[] arr2 = new int[max];
        for (int i = 0; i < max; i++) {
            arr2[i] = i + 1;
        }
        System.out.println("after change --> " + Arrays.toString(arr2));
    }
}

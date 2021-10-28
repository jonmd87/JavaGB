import java.util.Scanner;

public class TIcTocToe {
    private static char[] map = null;
    private static char DOT_empty = '*';
    private static char DOT_x = 'X';
    private static char DOT_0 = '0';
    private static int size = 3;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[] map = initMap();
        printMap(map);
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.printf("Please enter coordinats");
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while (!isCellValid(x,y));
        System.out.println("");
    }

    public static boolean isCellValid(int x, int y) {
        int sizePlusOne = size + 1;
        if (x > 0 && y > 0 && x < sizePlusOne && y < sizePlusOne) {
            int ind = x * y - 1;
            if (map[ind] == DOT_empty) {
                map[ind] = DOT_x;
                return true;
            }
            else {
                System.out.printf("!!!The coodinate is occuped!!!");
            }
        }
        System.out.printf("!!!Wroong coordinats(from 1 to %d )!!!\n", size);
        return false;
    }

    private static void printMap(char[] map) {
        for (int i = 0; i < map.length; i++) {
                System.out.print(map[i][y]);
                System.out.println("");
        }
    }

    public static char[] initMap() {
        char[] map = new char[size * size];
        for (int i = 0; i < size; i++) {
                map[i] = DOT_empty;
        }
        return (map);
    }
}

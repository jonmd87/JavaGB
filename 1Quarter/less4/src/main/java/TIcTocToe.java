import java.util.Random;
import java.util.Scanner;

public class TIcTocToe {
    public static char[][] map = null;
    public static char DOT_empty = '*';
    public static char DOT_x = 'X';
    public static char DOT_0 = '0';
    public static int size = 0; // запрашиваем размер поля
    public static int steps = 0; // подсчитывает количество шагов компа и игрока
    public static int maxSteps = 0; // максимальное количество шагов для игры.
    public static int sizeForWin = 0;  // количество фишек для победы
    public static int maxSize = 5;  // максимальная длина поля
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        getSizeSetSizes();
        map = initMap();
        while (steps < maxSteps) {
            printMap();
            humanTurn();
            aiTurn();
            if (steps / 2 > sizeForWin - 1 && whoWon()) // включаем проверку
                break;
        }
        if (steps == -1)
           if (!whoWon()) {System.out.println("No winner!!!");}
        printMap();
        System.out.println("Good bye!!!");
    }

    private static void getSizeSetSizes() {
        do {
            System.out.printf("Enter size of map(from 3 to %d): ", maxSize);
            size = scanner.nextInt();
        } while (size < 3 || size > maxSize);
        maxSteps = size * size;
        sizeForWin = size;
        if (size == maxSize) {sizeForWin = maxSize - (size / 3);}
    }

    private static boolean whoWon() {
        boolean player = false;
        boolean computer = false;
        player = checkWinner(DOT_x);
        computer = checkWinner(DOT_0);
        if (player && !computer)
            System.out.println("Player won!!!");
        else if (!player && computer)
            System.out.println("Computer won!!!");
        return (player || computer);
    }

    private static boolean checkWinner(char ch) {
        int i, y;
        // проверяем строчки
        for (i = 0; i < map.length; i++) {
            if (map[i][0] == ch) {
                for (y = 0; y < sizeForWin && map[i][y] == ch; y++);
                if (y == sizeForWin) {return true;}
            }
        }
        // проверяем столбцы
        for (i = 0; i < map.length; i++) {
            if (map[0][i] == ch) {
                for (y = 0; y < sizeForWin && map[y][i] == ch; y++);
                if (y == sizeForWin) {return true;}
            }
        }
        // проверяем диагональ с верхнего левого в нижний правый
        for (i = 0, y = 0; i < sizeForWin && map[i][y] == ch; i++, y++);
        if (i == sizeForWin && y == sizeForWin) {return true;}
        // проверяем диагональ с верхнего правого в нижний левый
        for (i = size - 1, y = 0; y < sizeForWin && map[i][y] == ch; i--, y++);
        if (i == -1 && y == sizeForWin) {return true;}
        return false;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (steps < maxSteps && map[y][x] != DOT_empty);
        map[y][x] = DOT_0;
        System.out.println("Computer made a step");
        steps++;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.printf("Now enter [X](columns) coordinats: ");
            x = scanner.nextInt();
            System.out.printf("Please enter [Y](rows) coordinats: ");
            y = scanner.nextInt();
        } while (steps < maxSteps && !isCellValid(x,y));
        System.out.printf("");
        steps++;
    }

    public static boolean isCellValid(int x, int y) {
        if (x > 0 && y > 0 && --x < size && --y < size) {
            if (map[y][x] == DOT_empty) {
                map[y][x] = DOT_x;
                return true;
            }
            else {
                System.out.printf("!!!The coodinate is occuped!!! + [%c]\n", map[y][x]);
                return false;
            }
        }
        System.out.printf("!!!Wroong coordinats(from 1 to %d )!!!\n", size);
        return false;
    }

    private static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int y = 0; y < map[i].length; y++) {
                System.out.print(map[i][y] + " ");
            }
            System.out.println("");
        }
    }

    public static char[][] initMap() {
        char[][] map = new char[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int y = 0; y < map[i].length; y++) {map[i][y] = DOT_empty;}
        }
        return (map);
    }
}

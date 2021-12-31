package ru.gb.gerasimenko;

import java.util.Random;

public class Obstruction {
    private static int max = 10;
    static Random random = new Random();

    public static void main( String[] args ) {
        Object[] players = createPlayers();
        Object[] obstructions = createObstructions();

        System.out.println("Our players:");
        showWhatEntity(players);
        System.out.println("\nMust cross the obstacle course:");
        showWhatEntity(obstructions);
        System.out.println("Let's start:");
        game(players, obstructions);
    }

    private static void game(Object[] players, Object[] obstructions) {
        if (players != null && obstructions != null) {
            for (Object p : players) {
                System.out.println("First player " + p.toString() + "started.");
                obstruction(p, obstructions);
            }
        }

    }

    private static void obstruction(Object player, Object[] obstructions) {
        Object p;
        if (player instanceof Cat) {
            p = (Cat) player;
        } else if (player instanceof Human) {
            p = (Human) player;
        } else if (player instanceof Robot) {
            p = (Robot) player;
        }
        for (Object o : obstructions) {
            player.
        }
    }

    private static Object[] createObstructions() {
        Object[] obstructions = new Object[max];

        for (int i = 0; i < obstructions.length; i++) {
            if (i % 2 == 0) {
                obstructions[i] = new Treadmill(random.nextInt((i + 1) * 100)); // bound must be positive
            }
            else {
                obstructions[i] = new Wall(random.nextInt(3)); // bound must be positive
            }
        }

        return obstructions;
    }


    private static Object[] createPlayers() {
        Object[] players = new Object[max];

        for (int i = 0; i < players.length; i++) {
            int ran = random.nextInt(max * (max + i));
            players[i] = createEntity(ran);
        }
        return players;
    }

    public static Object createEntity(int num) {
        if (num % 3 == 0) {
            return ((Object) new Robot());
        } else if (num % 2 == 0) {
            return ((Object) new Human());
        }
        return ((Object) new Cat());
    }

    public static void showWhatEntity(Object[] arr) {
        if (arr[0] instanceof Wall || arr[0] instanceof Treadmill) {
            System.out.println("START:");
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] instanceof Wall) {
                    System.out.println(arr[i].toString());
                }
                else {
                    System.out.println(arr[i].toString());
                }
            }
            System.out.println("FINISH.");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] instanceof Human) {
                System.out.println(((Human) arr[i]).getName());
            } else if (arr[i] instanceof Cat) {
                System.out.println(((Cat) arr[i]).getName());
            } else if (arr[i] instanceof Robot) {
                System.out.println(((Robot) arr[i]).getName());
            }
        }
    }
}

package ru.gb.gerasimenko;

import java.util.Random;

public class oApp {
    private static String BORDER = "------------------------------------\n";
    private static int max;
    private static int KM = 1000;
    static Random random = new Random();

    static {
        do {
            max = random.nextInt(20);
        }
        while (max < 10);
    }

    public static void main( String[] args ) {
        Actions[] players = createPlayers(max);
        showPlayers(players);
        Obstruction[] obstructions = createObstructions(max);
        showObstructions(obstructions);
        game(obstructions, players);

    }

    private static void game(Obstruction[] obstructions, Actions[] players) {
        if (players == null || obstructions == null) {
            System.out.println("Bad arguments!!");
        }
        System.out.println("Let's start the game:");
        for (Obstruction o : obstructions) {
            for(Actions p : players) {
                if (p.isActive()) {
                    crossObstruction(o, p);
                }
            }
        }
    }

    private static void crossObstruction(Obstruction o, Actions player) {
        System.out.printf("%sPlayer %s \tmust cross the obstruction %s\n", BORDER, player.toString(), o.toString());
        if (o instanceof Wall) {
            player.jump();
            player.setActive(o.passObstruction(player.getMaxJump()));
        }
        else if (o instanceof Treadmill) {
            player.run();
            player.setActive(o.passObstruction(player.getMaxRun()));
        }
        if (!player.isActive()) {
            System.out.printf("Player [%s] leaves our competition!\n", player.getName());
        }
        else {
            System.out.println("Player " + player.getName() + " crossed obstruction");
        }
    }


    private static void showObstructions(Obstruction[] obstructions) {
        System.out.println("\nMust pass the obstructions:");
        for (Obstruction o : obstructions) {
            System.out.printf("\t\t[%s]\n", o.getName());
        }
    }

    private static void showPlayers(Actions[] players) {
        System.out.println("Our players:");
        for (Actions a : players) {
            System.out.println(a.getName());
        }
    }

    private static Obstruction[] createObstructions(int max) {
        Obstruction[] obstructions = new Obstruction[max];

        for (int i = 0; i < obstructions.length; i++) {
            if (i % 2 == 0) {
                obstructions[i] = new Treadmill((i + 1) * App.KM);
            }
            else {
                obstructions[i] = new Wall(random.nextInt(max));
            }
        }

        return obstructions;
    }

    private static Actions[] createPlayers(int max) {
        Actions[] players = new Actions[max];
        for (int i = 0; i < players.length; i++) {
            int ran = random.nextInt(max * max);
            if (ran % 3 == 0) {
                players[i] = new Robot(KM * (i + random.nextInt(max)), random.nextInt(max));
            }
            else if (ran % 2 == 0) {
                players[i] = new Human(KM * (i + random.nextInt(max)), random.nextInt(max));
            }
            else {
                players[i] = new Cat(KM * (i + random.nextInt(max)), random.nextInt(max));
            }
        }
        return players;
    }
}

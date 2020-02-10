import java.util.*;

import items.*;

/**
 * @ - овца, > - волк.
 * Животные ходят только по точкам, овца - в любом направлении, волки - вниз.
 * Выигрывает игрок, если добирается до верхней строки поля. Выигрывают волки, если
 * им удается зажать игрока, окружив со всех сторон.
 * show - оформляет поле.
 */

public class Main {
    public static void main(String[] args) {
        Field field = new Field();

        Animal wolf1 = new Animal(0, 0, ">");
        Animal wolf2 = new Animal(0, 2, ">");
        Animal wolf3 = new Animal(0, 4, ">");
        Animal wolf4 = new Animal(0, 6, ">");

        wolf1.place(field.getField());
        wolf2.place(field.getField());
        wolf3.place(field.getField());
        wolf4.place(field.getField());

        List<Animal> wolves = new ArrayList<>();
        wolves.add(wolf1);
        wolves.add(wolf2);
        wolves.add(wolf3);
        wolves.add(wolf4);

        gameCycle(field, wolves);
    }

    public static void show(int[][] field) {
        StringBuilder res = new StringBuilder();

        res.append("# 0 1 2 3 4 5 6 7 #\n");
        for (int v = 0; v < 8; v++) {
            res.append(v).append(" ");
            for (int h = 0; h < 8; h++) {
                if (field[v][h] == 255) res.append("> ");
                else if (field[v][h] == 256) res.append("@ ");
                else if (v % 2 == 0 && h % 2 == 0) res.append(". ");
                else if (v % 2 != 0 && h % 2 != 0) res.append(". ");
                else res.append("  ");
            }
            res.append(v).append("\n");
        }
        res.append("# 0 1 2 3 4 5 6 7 #");

        System.out.println(res.toString());
    }

    private static void gameCycle(Field field, List<Animal> wolves) {
        Animal sheep = new Animal(7, 3, "@");
        sheep.place(field.getField());

        show(field.getField());

        Scanner move = new Scanner(System.in);

        boolean status = true;
        while (status) {
            if (sheep.getPlace().getV() == 0) {
                System.out.println("-:Win!");
                break;
            }

            List<Coord> variants = sheep.getPlace().sheepMoves();
            status = false;
            for (Coord var: variants) {
                if (field.getField()[var.getV()][var.getH()] == 0) status = true;
            }
            if (!status) {
                System.out.println("-:Lose!");
                break;
            }

            System.out.println("-:Next move?(up/down; left/right)");
            String updown = move.nextLine();
            String leftright = move.nextLine();

            if (!sheep.userMove(field.getField(), updown, leftright)) {
                System.out.println("-:Something wrong...");
                continue;
            }

            eva(field, wolves, sheep);

            show(field.getField());
        }
    }

    private static void eva(Field field, List<Animal> wolves, Animal sheep) {
        int[][] testField = CopyStuff.copyMatrix(field.getField());

        Animal wolf = wolves.get(3);
        int maxLen = 0;
        Coord pos = wolf.getPlace().wolfMoves().get(0);

        for (Animal w: wolves) {
            List<Coord> variants = w.getPlace().wolfMoves();

            for (Coord var: variants) {
                CopyStuff.moveCopy(testField, w.getPlace(), var);
                int curLen = sheep.pathToTop(testField);
                CopyStuff.moveCopy(testField, var, w.getPlace());

                if (curLen > maxLen && field.getField()[var.getV()][var.getH()] == 0)  {
                    maxLen = curLen;
                    wolf = w;
                    pos = var;
                }
            }
        }

        wolf.animalMove(field.getField(), pos.getV(), pos.getH());
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import items.*;
import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();

        Animal wolf1 = new Animal(0, 0, ">");
        Animal wolf2 = new Animal(0, 2, ">");
        Animal wolf3 = new Animal(0, 4, ">");
        Animal wolf4 = new Animal(0, 6, ">");

        field.place(wolf1);
        field.place(wolf2);
        field.place(wolf3);
        field.place(wolf4);

        List<Animal> wolfes = new ArrayList<>();
        wolfes.add(wolf1);
        wolfes.add(wolf2);
        wolfes.add(wolf3);
        wolfes.add(wolf4);

        gameCycle(field, wolfes);
    }

    private static void show(Field field) {
        StringBuilder res = new StringBuilder();
        Object[][] sh = field.getField();

        res.append("###############\n");
        for (int v = 0; v < 8; v++) {
            for (int h = 0; h < 8; h++) {
                res.append(sh[v][h]).append(" ");
            }
            res.append("\n");
        }
        res.append("###############");

        System.out.println(res.toString());
    }

    public static boolean move(Field field, Animal a, String updown, String leftright) {
        int newV = a.getPlace().getKey();
        int newH = a.getPlace().getValue();

        if (updown.equalsIgnoreCase("up")) {
            --newV;
            if (leftright.equalsIgnoreCase("left")) --newH;
            else if (leftright.equalsIgnoreCase("right")) ++newH;
            else return false;
        }
        else if (updown.equalsIgnoreCase("down")) {
            ++newV;
            if (leftright.equalsIgnoreCase("left")) --newH;
            else if (leftright.equalsIgnoreCase("right")) ++newH;
            else return false;
        }
        else return false;

        if (newV < 0 || newV > 7 || newH < 0 || newH > 7) return false;

        field.replace(a);
        a.setPlace(newV, newH);
        field.place(a);
        return true;
    }

    /*
    private static int next(Field field, Animal animal, boolean mark) {
        Field newField = new Field();
        Pair<Integer, Integer> animalPlace = animal.getPlace();
        int x = 0;
        int y = 0;

        if (mark) {
            if (move(newField, animal, "down", "left")) {
                return x = Math.max(next(newField, animal, false), next(newField, animal, false));
            }
            if (move(newField, animal, "down", "right")) {
                return y = Math.max(next(newField, animal, false), next(newField, animal, false));
            }
            return Math.max(x, y);
        }

        y = 0;
        for (int v = 0; v <= 7; v++) {
            for (int h = 0; h <= 7; h++) {
                if (field.getField()[v][h].equals(".")) {
                    x = Math.max((Math.abs(animalPlace.getKey() - v)), (Math.abs(animalPlace.getValue() - h)));
                    newField.getField()[v][h] = x;
                    if (y < x) y = x;
                }
                else newField.getField()[v][h] = field.getField()[v][h];
            }
        }

        show(newField);
        return y;
    }
     */

    public static int way(Field field, int verti, int hori, int count) {
        if (verti == 0) return count;

        for (int y = verti - 1; y <= verti + 1; y += 2) {
            for (int x = hori - 1; x <= hori + 1; x += 2) {
                if (y <= 7 && x <= 7 && x >= 0 && y >= 0) {
                    if (field.getField()[y][x].equals(".")) return way(field, y, x, ++count);
                }
            }
        }
        return 255;
    }

    private static void gameCycle(Field field, List<Animal> wolfes) {
        /*
        Scanner pos = new Scanner(System.in);
        System.out.println("-Where do we start?");
        int v = pos.nextInt();
        int h = pos.nextInt();
         */
        Animal sheep = new Animal(7, 3, "@");
        field.place(sheep);

        show(field);

        boolean status = true;
        Scanner move = new Scanner(System.in);
        while (status) {
            System.out.println("-:Next move?(up/down; left/right)");
            String updown = move.nextLine();
            String leftright = move.nextLine();
            if (!move(field, sheep, updown, leftright)) {
                System.out.println("-:Something wrong...");
                continue;
            }

            for (Animal w: wolfes) {

            }

            System.out.println(way(field, sheep.getPlace().getKey(), sheep.getPlace().getValue(), 0));
            show(field);
            status = false;
        }
    }
}

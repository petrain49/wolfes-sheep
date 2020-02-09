import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import items.*;
;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();

        Animal wolf1 = new Animal(0, 0, ">");
        Animal wolf2 = new Animal(0, 2, ">");
        Animal wolf3 = new Animal(0, 4, ">");
        Animal wolf4 = new Animal(1, 7, ">");

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

    public static void show(Object[][] field) {
        StringBuilder res = new StringBuilder();

        res.append("# 0 1 2 3 4 5 6 7 #\n");
        for (int v = 0; v < 8; v++) {
            res.append(v).append(" ");
            for (int h = 0; h < 8; h++) {
                res.append(field[v][h]).append(" ");
            }
            res.append(v).append("\n");
        }
        res.append("# 0 1 2 3 4 5 6 7 #");

        System.out.println(res.toString());
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

        show(field.getField());

        boolean status = true;
        Scanner move = new Scanner(System.in);
        while (status) {
            System.out.println("-:Next move?(up/down; left/right)");
            String updown = move.nextLine();
            String leftright = move.nextLine();

            if (!field.move(sheep, updown, leftright)) {
                System.out.println("-:Something wrong...");
                continue;
            }

            for (Animal w: wolfes) {
                List<Coord> moves = field.getNeighbours(w.getPlace());
            }

            System.out.println(sheep.path(field));
            show(field.getField());
            status = false;
        }
    }
}

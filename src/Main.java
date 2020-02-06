import java.util.*;
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

    private static void gameCycle(Field field, List<Animal> wolfes) {
        /*
        Scanner pos = new Scanner(System.in);
        System.out.println("-Where do we start?");
        int v = pos.nextInt();
        int h = pos.nextInt();
         */
        Animal sheep = new Animal(7, 3, "@");
        field.place(sheep);

        field.show();

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

            //for (Animal w: wolfes)

            int[][] done = new int[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) done[i][j] = 0;
            }

            System.out.println(done);
            System.out.println(field.path(field, sheep.getPlace().getV(), sheep.getPlace().getH(), 0, done));
            field.show();
            status = false;
        }
    }
}

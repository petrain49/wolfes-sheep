import java.util.*;

import items.*;

/**
 * @ - овца, > - волк.
 * Животные ходят только по точкам: овца - в любом направлении, волки - вниз.
 * Игрок выигрывает, если добирается до верхней строки поля. Выигрывают волки, если
 * им удается зажать игрока, окружив со всех сторон.
 * nextMove - позволяет игроку двигать овцу.
 * gameCycle - игровой цикл, выход из цикла только после победы/поражения игрока.
 */
public class Main {
    public static void main(String[] args) {
        Field field = new Field();

        WolfCoord wolf1 = new WolfCoord((byte) 0, (byte) 0);
        WolfCoord wolf2 = new WolfCoord((byte) 0, (byte) 2);
        WolfCoord wolf3 = new WolfCoord((byte) 0, (byte) 4);
        WolfCoord wolf4 = new WolfCoord((byte) 0, (byte) 6);

        wolf1.place(field);
        wolf2.place(field);
        wolf3.place(field);
        wolf4.place(field);

        List<WolfCoord> wolves = new ArrayList<>();
        wolves.add(wolf1);
        wolves.add(wolf2);
        wolves.add(wolf3);
        wolves.add(wolf4);

        gameCycle(field, wolves);
    }

    private static void nextMove(Field field, Scanner userMove, SheepCoord sheep) {
        System.out.println("-:Next move?(up/down; left/right)");
        String updown = userMove.nextLine();
        String leftright = userMove.nextLine();

        if (!sheep.userMove(field, updown, leftright)) {
            System.out.println("-:Something wrong...");
            nextMove(field, userMove, sheep);
        }
    }

    private static void gameCycle(Field field, List<WolfCoord> wolves) {
        SheepCoord sheep = new SheepCoord((byte) 7, (byte) 3);
        sheep.place(field);

        System.out.println(field.toString());

        Scanner userMove = new Scanner(System.in);

        boolean status = true;
        while (status) {
            nextMove(field, userMove, sheep); //следующий ход

            Threesome next = Eva04.eva04((byte) 3,
                                    field,
                                    sheep,
                                    wolves,
                                    new Threesome((byte) -1, wolves.get(0), wolves.get(0))); //выбор лучшего хода волка


            wolves.set(wolves.indexOf(next.getOldWolfCoord()), next.getNewWolfCoord());
            next.getOldWolfCoord().move(field, next.getNewWolfCoord().getV(), next.getNewWolfCoord().getH()); //ход волка

            System.out.println(field.toString());

            if (sheep.getV() == 0) {
                System.out.println("-:Победа!");
                break;
            }

            if (sheep.moves(field).isEmpty()) {
                System.out.println("-:Поражение!");
                break;
            }
        }
    }
}
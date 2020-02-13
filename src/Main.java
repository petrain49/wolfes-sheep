import java.util.*;

import items.*;

/**
 * @ - овца, > - волк.
 * Животные ходят только по точкам: овца - в любом направлении, волки - вниз.
 * Игрок выигрывает, если добирается до верхней строки поля. Выигрывают волки, если
 * им удается зажать игрока, окружив со всех сторон.
 * show - оформляет поле.
 * nextMove - позволяет игроку двигать овцу.
 * gameCycle - игровой цикл, выход из цикла только после победы/поражения игрока.
 * eva3 - алгоритм минимакса, определяет волка и его следующий ход.
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

    private static void show(int[][] field) {
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

    private static void nextMove(Field field, Scanner userMove, Animal sheep) {
        System.out.println("-:Next move?(up/down; left/right)");
        String updown = userMove.nextLine();
        String leftright = userMove.nextLine();

        if (!sheep.userMove(field.getField(), updown, leftright)) {
            System.out.println("-:Something wrong...");
            nextMove(field, userMove, sheep);
        }
    }

    private static void gameCycle(Field field, List<Animal> wolves) {
        Animal sheep = new Animal(7, 3, "@");
        sheep.place(field.getField());

        show(field.getField());

        Scanner userMove = new Scanner(System.in);

        boolean status = true;
        while (status) {
            nextMove(field, userMove, sheep);

            int[][] testField = Matrix.copyMatrix(field.getField());
            List<Coord> copyWolves = Matrix.animalListToCoordList(wolves);

            Object[] nextWolfMove =
                    eva4(3,
                            testField,
                            sheep.getPlace(),
                            copyWolves,
                            new Object[] {0, -1, sheep.getPlace()}); //выбор волка и его следующего хода

            if ((Integer) nextWolfMove[0] != -1) {
                wolves.get((Integer) nextWolfMove[1]).animalMove(
                        field.getField(),
                        ((Coord) nextWolfMove[2]).getV(),
                        ((Coord) nextWolfMove[2]).getH()); //ход волка
            }

            show(field.getField());

            if (sheep.getPlace().getV() == 0) {
                System.out.println("-:Win!");
                break;
            }

            List<Coord> variants = sheep.getPlace().sheepMoves(field.getField()); //возможные ходы овцы
            status = false;
            for (Coord var : variants) {
                if (field.getField()[var.getV()][var.getH()] == 0) status = true;
            }
            if (!status) {
                System.out.println("-:Lose!");
                break;
            }
        }
    }

    private static Object[] eva4(int depth,
                                 int[][] testField,
                                 Coord sheepPos,
                                 List<Coord> wolves,
                                 Object[] ans) {
        // ans[Integer evaluate, Integer wolf, Coord wolfMove] - оценка, номер волка, координаты волка
        if (sheepPos.getV() == 0 || depth == 0) return ans;

        Coord prevWolf;
        int evaluation = (Integer) ans[0]; //оценка
        int bestWolf = 0;
        Coord bestWolfMove = new Coord(-1, -1);

        int max = Integer.MIN_VALUE;
        for (int curWolf = 0; curWolf < wolves.size(); curWolf++) { //для каждого волка
            if (wolves.get(curWolf).wolfMoves(testField).isEmpty()) continue; //если ходов нет - пропускаем волка

            prevWolf = wolves.get(curWolf); //сохраняем позицию волка

            for (Coord wolfMove: wolves.get(curWolf).wolfMoves(testField)) { //для каждого хода текущего волка
                Matrix.moveInCopy(testField, wolves.get(curWolf), wolfMove); //двигаем волка
                wolves.set(curWolf, wolfMove); //закрепляем новые координаты волка в списке wolves

                if (sheepPos.sheepMoves(testField).isEmpty()) { //если у овцы нет ходов, возвращаем оценку, волка и его координаты
                    bestWolfMove.setVH(wolfMove.getV(), wolfMove.getH());
                    return new Object[] {evaluation, curWolf, bestWolfMove};
                }

                int min = Integer.MAX_VALUE;
                for (Coord sheepMove: sheepPos.sheepMoves(testField)) { //для каждого хода доступного овце
                    Matrix.moveInCopy(testField, sheepPos, sheepMove); //двигаем овцу

                    if (depth == 1) { //оценка на глубине 1
                        if (sheepMove.pathToTop(testField) < min) {
                            min = sheepMove.pathToTop(testField);
                        }
                    }
                    else { //оценка на глубине выше 1
                        Object[] recursion =
                                eva4(depth - 1,
                                        Matrix.copyMatrix(testField),
                                        sheepMove.clone(), Coord.copyCoordList(wolves),
                                        new Object[] {min, curWolf, wolfMove.clone()});

                        if ((int) recursion[0] < min) min = (Integer) recursion[0];
                    }
                    Matrix.moveInCopy(testField, sheepMove, sheepPos); //двигаем овцу обратно
                }

                if (min > max) {
                    max = min;
                    bestWolf = curWolf;
                    bestWolfMove.setVH(wolfMove.getV(), wolfMove.getH());
                }

                wolves.set(curWolf, prevWolf); //возвращаем старые координаты волку в списке wolves
                Matrix.moveInCopy(testField, wolfMove, wolves.get(curWolf)); //двигаем волка обратно
            }
        }
        ans = new Object[] {max, bestWolf, bestWolfMove};
        return ans;
    }
}
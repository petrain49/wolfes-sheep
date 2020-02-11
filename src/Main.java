import java.util.*;

import items.*;
import javafx.util.Pair;

/**
 * @ - овца, > - волк.
 * Животные ходят только по точкам, овца - в любом направлении, волки - вниз.
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

            Pair<Animal, Coord> next = eva3(1, field.getField(), sheep.getPlace(), wolves); //выбор волка и его следующего хода
            next.getKey().animalMove(field.getField(), next.getValue().getV(), next.getValue().getH()); //ход волка

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

    private static Pair<Animal, Coord> eva3(int depth, int[][] field, Coord sheepPos, List<Animal> wolves) {
        //if (depth == 0) return sheepPos;
        int[][] testField = Matrix.copyMatrix(field);

        Coord bestWolfMove = wolves.get(0).getPlace();
        Coord bestSheepMove = sheepPos;
        Animal bestWolf = wolves.get(0);
        int min = 255;
        int max = 0;

        for (Animal wolf: wolves){
            for (Coord wolfMove: wolf.getPlace().wolfMoves(testField)) { //все ходы доступные текущему волку
                Matrix.moveInCopy(testField, wolf.getPlace(), wolfMove); //двигаем волка
                //current = sheepPos.pathToTop(testField); //оценка с текущим положением овцы

                for (Coord sheepMove: sheepPos.sheepMoves(testField)) { //все ходы доступные овце
                    Matrix.moveInCopy(testField, sheepPos, sheepMove); //двигаем овцу

                    if (sheepMove.pathToTop(testField) < min) { //если оценка уменьшилась после хода овцы
                        min = sheepMove.pathToTop(testField);
                        bestSheepMove = sheepMove;
                    }
                    else if (sheepMove.pathToTop(testField) > max) {
                        max = sheepMove.pathToTop(testField);
                        bestWolfMove = wolfMove;
                        bestWolf = wolf;
                    }
                    Matrix.moveInCopy(testField, sheepMove, sheepPos); //двигаем овцу обратно
                }
                Matrix.moveInCopy(testField, wolfMove, wolf.getPlace()); //двигаем волка обратно
            }
        }

        //Matrix.moveInCopy(testField, bestWolf.getPlace(), bestWolfMove);
        //Matrix.moveInCopy(testField, sheepPos, bestSheepMove);

        return new Pair<>(bestWolf, bestWolfMove);
    }
}
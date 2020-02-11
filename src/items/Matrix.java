package items;

import javafx.util.Pair;

import java.util.List;

/**
 * Класс для работы с матрицами.
 * moveCopy - двигает число.
 * copyMatrix - копирует поле.
 */

public class Matrix {
    protected static boolean checkPos(int[][] matrix, int v, int h) {
        if (v >= 0 && v <= 7 && h >= 0 && h <= 7) return (matrix[v][h] == 0);
        return false;
    }

    public static void moveInCopy(int[][] matrix, Coord start, Coord end) {
        int num = matrix[start.getV()][start.getH()];

        if (!checkPos(matrix, end.getV(), end.getH())) throw new IllegalArgumentException("Wrong place");

        matrix[end.getV()][end.getH()] = num;
        matrix[start.getV()][start.getH()] = 0;
    }

    public static int[][] copyMatrix(int[][] field) {
        int[][] done = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                done[i][j] = field[i][j];
            }
        }
        return done;
    }

    public static Pair<Coord, Coord> wolfBestMove(int[][] field, Coord sheep, List<Coord> wolves) {
        int[][] testField = Matrix.copyMatrix(field);

        int curLen;
        int maxLen = 0;
        Coord wolf = wolves.get(3);
        Coord pos = wolf.wolfMoves(testField).get(0);
        List<Coord> variants;

        for (Coord w: wolves) {
            if (checkPos(testField, w.getV(), w.getV())) continue;

            variants = w.wolfMoves(testField); //все возможные ходы волка

            for (Coord var: variants) {
                Matrix.moveInCopy(testField, w, var);
                curLen = sheep.pathToTop(testField);
                Matrix.moveInCopy(testField, var, w);

                if (curLen > maxLen) {
                    maxLen = curLen;
                    wolf = w;
                    pos = var;
                    //System.out.println(var.getV() + " " + var.getH());
                    //System.out.println(maxLen);
                }
            }
        }
        return new Pair<Coord, Coord>(wolf, pos);
    }
}

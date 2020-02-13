package items;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с матрицами.
 * moveCopy - двигает число.
 * copyMatrix - копирует поле.
 * wolfsCoords - превращает список объектов Animal в список объектов Coord.
 */

public class Matrix {
    protected static boolean checkPos(int[][] matrix, int v, int h) {
        if (v >= 0 && v <= 7 && h >= 0 && h <= 7) return (matrix[v][h] == 0);
        return false;
    }

    public static void moveInCopy(int[][] matrix, Coord start, Coord end) {
        int num = matrix[start.getV()][start.getH()];

        if (!checkPos(matrix, end.getV(), end.getH())) {
            int guilty = matrix[end.getV()][end.getH()];
            throw new IllegalArgumentException("Wrong place " + num + " "
                    + start.getV() + " " + start.getH()
                    + " -> " + " " + guilty + " " + end.getV() + " " + end.getH());
        }

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

    public static List<Coord> animalListToCoordList(List<Animal> wolves) {
        List<Coord> ans = new ArrayList<>();
        for (Animal a: wolves) ans.add(a.getPlace());
        return ans;
    }
}

package items;

/**
 * Класс для работы с матрицами.
 * moveCopy - двигает число.
 * copyMatrix - копирует поле.
 */

public class CopyStuff {
    public static void moveCopy(int[][] matrix, Coord start, Coord end) {
        int num = matrix[start.getV()][start.getH()];

        if (!Field.checkPos(end.getV(), end.getH())) throw new IllegalArgumentException("Wrong place");

        if (matrix[end.getV()][end.getH()] == 0) {
            matrix[end.getV()][end.getH()] = num;
            matrix[start.getV()][start.getH()] = 0;
        }
    }

    public static int[][] copyMatrix(int[][] matrix) {
        int[][] done = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                done[i][j] = matrix[i][j];
            }
        }
        return done;
    }
}

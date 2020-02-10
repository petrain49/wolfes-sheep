package items;

public class Field {
    private int[][] field;

    public Field() {
        this.field = new int[8][8];
        for (int v = 0; v < 8; v++) {
            for (int h = 0; h < 8; h++) {
                this.field[v][h] = 0;
            }
        }
    }

    public int[][] getField() { return this.field; }

    public void setField(int[][] field) { this.field = field; }

    public static boolean checkPos(int v, int h) {
        return (v >= 0 && v <= 7 && h >= 0 && h <= 7);
    }
}

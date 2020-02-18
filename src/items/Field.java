package items;

/**
 * Класс поля.
 * matrix - двумерный массив, заполненный 0.
 * getCoordMatrix - возвращает число из матрицы по индексам.
 * isMatch - свободно ли место на поле.
 * getCopyMatrix - возвращает копию matrix.
 */
public class Field implements Cloneable {
    private byte[][] matrix;

    public Field() {
        this.matrix = new byte[8][8];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("# 0 1 2 3 4 5 6 7 #\n");
        for (byte v = 0; v < 8; v++) {
            res.append(v).append(" ");
            for (byte h = 0; h < 8; h++) {
                if (getCoordValue(v, h) == 120) res.append("> ");
                else if (getCoordValue(v, h) == 125) res.append("@ ");
                else if (v % 2 == 0 && h % 2 == 0) res.append(". ");
                else if (v % 2 != 0 && h % 2 != 0) res.append(". ");
                else res.append("  ");
            }
            res.append(v).append("\n");
        }
        res.append("# 0 1 2 3 4 5 6 7 #");

        return res.toString();
    }

    @Override
    protected Field clone() {
        byte[][] copyMatrix = getCopyMatrix();
        Field copyField = new Field();
        copyField.setMatrix(copyMatrix);
        return copyField;
    }

    public byte[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(byte[][] field) {
        this.matrix = field;
    }

    public byte getCoordValue(byte v, byte h) {
        return getMatrix()[v][h];
    }

    public void setCoordValue(byte num, byte v, byte h) {
        getMatrix()[v][h] = num;
    }

    public boolean isMatch(byte v, byte h) {
        if (v >= 0 && v <= 7 && h >= 0 && h <= 7) return (getCoordValue(v, h) == 0);
        return false;
    }

    public byte[][] getCopyMatrix() {
        byte[][] done = new byte[8][8];
        for (byte v = 0; v < 8; v++) {
            for (byte h = 0; h < 8; h++) {
                done[v][h] = getCoordValue(v, h);
            }
        }
        return done;
    }
}

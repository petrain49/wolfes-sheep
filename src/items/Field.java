package items;

import java.util.*;

public class Field {
    String[][] field;

    public Field() {
        field = new String[8][8];
        for (int v = 0; v < 8; v++) {
            for (int h = 0; h < 8; h++) {
                field[v][h] = ".";
            }
        }
    }

    public void place(Animal a) {
        String[][] field = getField();
        int v = a.getPlace().getKey();
        int h = a.getPlace().getValue();
        field[v][h] = a.getSpecies();
    }

    public String show() {
        StringBuilder res = new StringBuilder();
        String[][] field = getField();
        for (int v = 0; v < 8; v++) {
            for (int h = 0; h < 8; h++) {
                res.append(field[v][h]).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public String[][] getField() { return this.field; }

    public void setField(String[][] field) { this.field = field; }
}

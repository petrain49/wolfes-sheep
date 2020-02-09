package items;

import java.util.ArrayList;
import java.util.List;

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

    public void place(Animal a) {
        int[][] field = getField();
        int v = a.getPlace().getV();
        int h = a.getPlace().getH();

        int num;
        if (a.getSpecies().equals("@")) num = 256;
        else num = 255;


        if (v % 2 == 0 && h % 2 == 0) field[v][h] = num;
        else if (v % 2 != 0 && h % 2 != 0) field[v][h] = num;
        else throw new IllegalArgumentException("Wrong place");
    }

    public void replace(Animal a) {
        int[][] field = getField();
        int v = a.getPlace().getV();
        int h = a.getPlace().getH();

        field[v][h] = 0;
    }

    public int[][] getField() { return this.field; }

    public boolean move(Animal a, String updown, String leftright) {
        int newV = a.getPlace().getV();
        int newH = a.getPlace().getH();

        if (updown.equalsIgnoreCase("up")) {
            --newV;
            if (leftright.equalsIgnoreCase("left")) --newH;
            else if (leftright.equalsIgnoreCase("right")) ++newH;
            else return false;
        }
        else if (updown.equalsIgnoreCase("down")) {
            ++newV;
            if (leftright.equalsIgnoreCase("left")) --newH;
            else if (leftright.equalsIgnoreCase("right")) ++newH;
            else return false;
        }
        else return false;

        if (!Coord.checkPos(newV, newH)) return false;

        replace(a);
        a.setPlace(newV, newH);
        place(a);
        return true;
    }

    public int[][] copy() {
        int[][] done = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) done[i][j] = getField()[i][j];
        }
        return done;
    }
}

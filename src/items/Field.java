package items;

import java.util.ArrayList;
import java.util.List;

/**
 * if (v % 2 == 0 && h % 2 == 0) this.field[v][h] = ".";
 *                 else if (v % 2 != 0 && h % 2 != 0) this.field[v][h] = ".";
 *                 else this.field[v][h] = " ";
 */

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

    public boolean checkPos(int v, int h) {
        return (v >= 0 && v <= 7 && h >= 0 && h <= 7); //&& field[v][h].equals("."));
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

        if (!checkPos(newV, newH)) return false;

        replace(a);
        a.setPlace(newV, newH);
        place(a);
        return true;
    }

    public List<Coord> getNeighbours(Coord pos) {
        List<Coord> neighbours = new ArrayList<>();
        for (int v = pos.getV() - 1; v <= pos.getV() + 1; v += 2) {
            for (int h = pos.getH() - 1; h <= pos.getH() + 1; h += 2) {
                if (checkPos(v, h)) {
                    Coord neighbour = new Coord(v, h);
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    public int[][] copy() {
        int[][] done = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) done[i][j] = getField()[i][j];
        }
        return done;
    }
}

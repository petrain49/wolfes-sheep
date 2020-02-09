package items;

import java.util.ArrayList;
import java.util.List;

public class Coord {
    private int v;
    private int h;

    Coord(int nV, int nH) {
        if (nV < 8 && nV > -1 && nH < 8 && nH > -1) {
            setV(nV);
            setH(nH);
        }
        else throw new IllegalArgumentException();
    }

    public int getV() { return this.v; }

    public void setV(int y) { this.v = y; }

    public int getH() { return this.h; }

    public void setH(int x) { this.h = x; }

    public static boolean checkPos(int v, int h) {
        return (v >= 0 && v <= 7 && h >= 0 && h <= 7); //&& field[v][h].equals("."));
    }

    public List<Coord> getNeighbours() {
        int posV = getV();
        int posH = getH();
        List<Coord> neighbours = new ArrayList<>();
        for (int v = posV - 1; v <= posV + 1; v += 2) {
            for (int h = posH - 1; h <= posH + 1; h += 2) {
                if (Coord.checkPos(v, h)) {
                    Coord neighbour = new Coord(v, h);
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }
}

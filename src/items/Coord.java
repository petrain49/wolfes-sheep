package items;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс координат.
 * sheepMoves - возвращает список доступных клеток для овцы.
 * wolfMoves - возвращает список доступных клеток для волка.
 * Методы находятся вне класса Animal для удобства работы с матрицами.
 */

public class Coord {
    private int v;
    private int h;

    public Coord(int nV, int nH) {
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

    public List<Coord> sheepMoves() {
        int posV = getV();
        int posH = getH();
        List<Coord> neighbours = new ArrayList<>();
        for (int v = posV - 1; v <= posV + 1; v += 2) {
            for (int h = posH - 1; h <= posH + 1; h += 2) {
                if (Field.checkPos(v, h)) {
                    Coord neighbour = new Coord(v, h);
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    public List<Coord> wolfMoves() {
        int posV = getV() + 1;
        int posH = getH();
        List<Coord> moves = new ArrayList<>();
        for (int h = posH - 1; h <= posH + 1; h += 2) {
            if (Field.checkPos(posV, h)) {
                Coord neighbour = new Coord(posV, h);
                moves.add(neighbour);
            }
        }
        return moves;
    }
}

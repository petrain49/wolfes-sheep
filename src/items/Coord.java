package items;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Класс координат.
 * sheepMoves - возвращает список доступных клеток для овцы.
 * wolfMoves - возвращает список доступных клеток для волка.
 * pathToTop - волновой алгоритм, возвращающий длину кратчайшего пути от
 * животного(обычно овцы) к верхней строке поля.
 * Методы находятся вне класса Animal для удобства работы с матрицами.
 */

public class Coord implements Cloneable {
    private int v;
    private int h;

    public Coord(int nV, int nH) {
        setV(nV);
        setH(nH);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Coord sec = (Coord) obj;
        return (this.getV() == sec.getV() && this.getH() == sec.getH());
    }

    @Override
    public Coord clone()  {
        return new Coord(this.getV(), this.getH());
    }

    public int getV() { return this.v; }

    public void setV(int y) { this.v = y; }

    public int getH() { return this.h; }

    public void setH(int x) { this.h = x; }

    public void setVH(int y, int x) {
        this.v = y;
        this.h = x;
    }

    public List<Coord> sheepMoves(int[][] field) {
        int posV = getV();
        int posH = getH();
        List<Coord> neighbours = new ArrayList<>();
        for (int v = posV - 1; v <= posV + 1; v += 2) {
            for (int h = posH - 1; h <= posH + 1; h += 2) {
                if (Matrix.checkPos(field, v, h)) {
                    Coord neighbour = new Coord(v, h);
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    public List<Coord> wolfMoves(int[][] field) {
        int posV = getV() + 1;
        int posH = getH();
        List<Coord> moves = new ArrayList<>();
        for (int h = posH - 1; h <= posH + 1; h += 2) {
            if (Matrix.checkPos(field, posV, h)) {
                Coord neighbour = new Coord(posV, h);
                moves.add(neighbour);
            }
        }
        return moves;
    }

    public int pathToTop(int[][] field) {
        if (getV() == 0) return 0;

        int[][] done = Matrix.copyMatrix(field);
        List<Coord> que = new ArrayList<>();
        que.add(new Coord(getV(), getH()));
        done[getV()][getH()] = 256;

        int count = 1;
        boolean finish = false;

        while (!finish) {
            finish = true;
            for (ListIterator<Coord> iterator = que.listIterator(); iterator.hasNext();){
                List<Coord> moves = iterator.next().sheepMoves(field);
                for (Coord m : moves) {
                    if (done[m.getV()][m.getH()] == 0) {
                        done[m.getV()][m.getH()] = count;
                        iterator.add(m);
                        finish = false;
                    }
                }
            }
            ++count;
        }

        int min = 255;
        for (int m: new int[] {done[0][0], done[0][2], done[0][4], done[0][6]}) if (m > 0 && m < min) min = m;
        return min;
    }

    public static List<Coord> copyCoordList(List<Coord> wolves) {
        List<Coord> ans = new ArrayList<>();
        for (Coord a: wolves) ans.add(a.clone());
        return ans;
    }
}

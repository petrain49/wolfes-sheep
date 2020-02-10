package items;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Содержит координаты и вид животного.
 * pathToTop - волновой алгоритм, возвращающий длину кратчайшего пути от животного(обычно овцы) к верхней строке поля.
 * place - размещает животное на поле.
 * replaceWithZero - убирает животное с поля.
 * animalMove - перемещает животное в заданные координаты(метод не для игрока!).
 * userMove - перемещает животное на одну клетку в заданном направлении(только для игрока!), возвращает
 * false если направление ведет за границы поля или заданы неверные аргументы.
 */

public class Animal {
    private int verti;
    private int hori;
    private String species;

    public Animal(int v, int h, String species) {
        this.verti = v;
        this.hori = h;
        this.species = species;
    }

    public Coord getPlace() {
        return new Coord(this.verti, this.hori);
    }

    public void setPlace(int verti, int hori) {
        this.verti = verti;
        this.hori = hori;
    }

    public String getSpecies() {
        return this.species;
    }

    public int pathToTop(int[][] matrix) {
        if (getPlace().getV() == 0) return 0;

        int[][] done = CopyStuff.copyMatrix(matrix);
        List<Coord> que = new ArrayList<>();
        que.add(getPlace());
        done[getPlace().getV()][getPlace().getH()] = 256;

        int count = 1;
        boolean finish = false;

        while (!finish) {
            finish = true;
            for (ListIterator<Coord> iterator = que.listIterator(); iterator.hasNext();){
                List<Coord> moves = iterator.next().sheepMoves();
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

        int[] top = new int[] {done[0][0], done[0][2], done[0][4], done[0][6]};
        int min = 255;
        for (int m: top) if (m > 0 && m < min) min = m;
        return min;
    }

    public void place(int[][] field) {
        //int[][] matrix = field.getField();
        int v = getPlace().getV();
        int h = getPlace().getH();

        if (field[v][h] != 0) throw new IllegalArgumentException();

        int num;
        if (getSpecies().equals("@")) num = 256;
        else num = 255;


        if (v % 2 == 0 && h % 2 == 0) field[v][h] = num;
        else if (v % 2 != 0 && h % 2 != 0) field[v][h] = num;
        else throw new IllegalArgumentException();
    }

    public void replaceWithZero(int[][] field) {
        //int[][] matrix = field.getField();
        int v = getPlace().getV();
        int h = getPlace().getH();

        field[v][h] = 0;
    }

    public void animalMove(int[][] field, int v, int h) {
        replaceWithZero(field);
        setPlace(v, h);
        place(field);
    }

    public boolean userMove(int[][] field, String updown, String leftright) {
        int newV = getPlace().getV();
        int newH = getPlace().getH();

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

        if (!Field.checkPos(newV, newH)) return false;

        animalMove(field, newV, newH);

        return true;
    }
}
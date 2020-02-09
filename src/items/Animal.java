package items;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


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

    public int path(Field field) {
        if (getPlace().getV() == 0) return 0;

        Coord pos = getPlace();
        int count = 1;

        int[][] done = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) done[i][j] = 0;
        }

        List<Coord> que = new ArrayList<>();
        que.add(pos);
        done[pos.getV()][pos.getH()] = 255;

        while (done[0][0] == 0 || done[0][2] == 0 || done[0][4] == 0 || done[0][6] == 0) {
            for (ListIterator<Coord> iterator = que.listIterator(); iterator.hasNext();){
                List<Coord> neighbours = field.getNeighbours(iterator.next());
                for (Coord n : neighbours) {
                    if (field.getField()[n.getV()][n.getH()].equals(".")) {
                        if (done[n.getV()][n.getH()] == 0) {
                            done[n.getV()][n.getH()] = count;
                            iterator.add(n);
                        }
                    }
                    else done[n.getV()][n.getH()] = 255;
                }
            }
            ++count;
        }
        return Math.min(Math.min(Math.min(done[0][0], done[0][2]), done[0][4]), done[0][6]);
    }


}
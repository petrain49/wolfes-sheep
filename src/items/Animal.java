package items;

import javafx.util.Pair;

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
}
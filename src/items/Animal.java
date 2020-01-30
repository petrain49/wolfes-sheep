package items;

import javafx.util.Pair;

public class Animal {
    private int verti;
    private int hori;
    private String species;

    public Animal(int verti, int hori, String species) {
        this.verti = verti;
        this.hori = hori;
        this.species = species;
    }

    public Pair<Integer, Integer> getPlace() {
        return new Pair<Integer, Integer>(this.verti, this.hori);
    }

    public void setPlace(int verti, int hori) {
        this.verti = verti;
        this.hori = hori;
    }

    public String getSpecies() {
        return this.species;
    }
}
package items;

import java.util.*;

public class Field {
    private String[][] field;

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

    public void place(Animal a, String sym) {
        String[][] field = getField();
        int v = a.getPlace().getKey();
        int h = a.getPlace().getValue();

        field[v][h] = sym;
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

    public void move(Animal a,String updown, String leftright) {
        place(a, ".");

        int newV = a.getPlace().getKey();
        int newH = a.getPlace().getValue();

        assert(newV >= 0 && newV < 8);
        assert(newH >= 0 && newH < 8);

        if (updown.equalsIgnoreCase("up")) {
            newV--;
            if (leftright.equalsIgnoreCase("left")) {
                newH--;
                a.setPlace(newV, newH);
            }
            else if (leftright.equalsIgnoreCase("right")) {
                newH++;
                a.setPlace(newV, newH);
            }
            else throw new IllegalArgumentException();
        }
        else if (updown.equalsIgnoreCase("down")) {
            newV++;
            if (leftright.equalsIgnoreCase("left")) {
                newH--;
                a.setPlace(newV, newH);
            }
            else if (leftright.equalsIgnoreCase("right")) {
                newH++;
                a.setPlace(newV, newH);
            }
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        place(a);
    }

    public String[][] getField() { return this.field; }

    public void setField(String[][] field) { this.field = field; }
}

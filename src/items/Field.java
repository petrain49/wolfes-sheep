package items;

public class Field {
    private Object[][] field;

    public Field() {
        this.field = new Object[8][8];
        for (int v = 0; v < 8; v++) {
            for (int h = 0; h < 8; h++) {
                if (v % 2 == 0 && h % 2 == 0) this.field[v][h] = ".";
                else if (v % 2 != 0 && h % 2 != 0) this.field[v][h] = ".";
                else this.field[v][h] = " ";
            }
        }
    }

    public void place(Animal a) {
        Object[][] field = getField();
        int v = a.getPlace().getKey();
        int h = a.getPlace().getValue();

        if (v % 2 == 0 && h % 2 == 0) field[v][h] = a.getSpecies();
        else if (v % 2 != 0 && h % 2 != 0) field[v][h] = a.getSpecies();
        else throw new IllegalArgumentException("Wrong place");
    }

    public void replace(Animal a) {
        Object[][] field = getField();
        int v = a.getPlace().getKey();
        int h = a.getPlace().getValue();

        if (v % 2 == 0 && h % 2 == 0) field[v][h] = ".";
        else if (v % 2 != 0 && h % 2 != 0) field[v][h] = ".";
        else field[v][h] = " ";
    }

    public Object[][] getField() { return this.field; }
}

package items;

public class Field {
    private String[][] field;

    public Field() {
        this.field = new String[8][8];
        for (int v = 0; v < 8; v++) {
            for (int h = 0; h < 8; h++) {
                if (v % 2 == 0 && h % 2 == 0) this.field[v][h] = ".";
                else if (v % 2 != 0 && h % 2 != 0) this.field[v][h] = ".";
                else this.field[v][h] = " ";
            }
        }
    }

    public boolean pos(int v, int h) {
        return v >= 0 && v <= 7 && h >= 0 && h <= 7;
    }

    public void place(Animal a) {
        String[][] field = getField();
        int v = a.getPlace().getV();
        int h = a.getPlace().getH();

        if (v % 2 == 0 && h % 2 == 0) field[v][h] = a.getSpecies();
        else if (v % 2 != 0 && h % 2 != 0) field[v][h] = a.getSpecies();
        else throw new IllegalArgumentException("Wrong place");
    }

    public void replace(Animal a) {
        String[][] field = getField();
        int v = a.getPlace().getV();
        int h = a.getPlace().getH();

        if (v % 2 == 0 && h % 2 == 0) field[v][h] = ".";
        else if (v % 2 != 0 && h % 2 != 0) field[v][h] = ".";
        else field[v][h] = " ";
    }

    public String[][] getField() { return this.field; }

    public void show() {
        StringBuilder res = new StringBuilder();
        Object[][] sh = getField();

        res.append("# 0 1 2 3 4 5 6 7 #\n");
        for (int v = 0; v < 8; v++) {
            res.append(v).append(" ");
            for (int h = 0; h < 8; h++) {
                res.append(sh[v][h]).append(" ");
            }
            res.append(v).append("\n");
        }
        res.append("# 0 1 2 3 4 5 6 7 #");

        System.out.println(res.toString());
    }

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

        if (pos(newV, newH)) return false;

        replace(a);
        a.setPlace(newV, newH);
        place(a);
        return true;
    }

    public int path(Field field, int v, int h, int count, int[][] done) {

        while (done[v][0] == 0 || done[v][2] == 0 || done[v][4] == 0 || done[v][6] == 0) {
            count = 0;
            for (int y = v - 1; y <= v + 1; y += 2) {
                for (int x = h - 1; x <= h + 1; x += 2) {
                    if (pos(y, x)) {
                        if (field.getField()[y][x].equals(".") && done[y][x] == 0) {
                            ++count;
                            done[y][x] = count;
                            System.out.println(y);
                            System.out.println(x);
                        }
                    }
                }
            }
        }

        return count;
    }
}

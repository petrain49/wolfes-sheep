package items;

/**
 * Содержит координаты и вид животного.
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

    public void place(int[][] field) {
        int v = getPlace().getV();
        int h = getPlace().getH();

        if (!Matrix.checkPos(field, v, h))
            throw new IllegalArgumentException("cant place to " + Integer.toString(v) + " " + Integer.toString(h));

        int num;
        if (getSpecies().equals("@")) num = 256;
        else num = 255;


        if (v % 2 == 0 && h % 2 == 0) field[v][h] = num;
        else if (v % 2 != 0 && h % 2 != 0) field[v][h] = num;
        else throw new IllegalArgumentException();
    }

    private void replaceWithZero(int[][] field) {
        int v = getPlace().getV();
        int h = getPlace().getH();

        field[v][h] = 0;
    }

    public boolean animalMove(int[][] field, int v, int h) {
        if (!Matrix.checkPos(field, v, h)) return false;
        replaceWithZero(field);
        setPlace(v, h);
        place(field);
        return true;
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

        return animalMove(field, newV, newH);
    }
}
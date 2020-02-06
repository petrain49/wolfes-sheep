package items;

public class Coord {
    private int v;
    private int h;

    Coord(int nV, int nH) {
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
}

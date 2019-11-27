import javafx.util.Pair;

public class Animal {
    private int verti;
    private int hori;

    Animal(int verti, int hori) {
        this.verti = verti;
        this.hori = hori;
    }

    public void move(String updown, String leftright) {
        int newVerti = getPlace().getKey();
        int newHori = getPlace().getValue();

        assert(newVerti >= 0 && newVerti < 8);
        assert(newHori >= 0 && newHori < 8);

        if (updown.equalsIgnoreCase("up")) {
            newVerti--;
            if (leftright.equalsIgnoreCase("left")) {
                newHori--;
                setPlace(newVerti, newHori);
            }
            else if (leftright.equalsIgnoreCase("right")) {
                newHori++;
                setPlace(newVerti, newHori);
            }
            else throw new IllegalArgumentException();
        }
        else if (updown.equalsIgnoreCase("down")) {
            newVerti++;
            if (leftright.equalsIgnoreCase("left")) {
                newHori--;
                setPlace(newVerti, newHori);
            }
            else if (leftright.equalsIgnoreCase("right")) {
                newHori++;
                setPlace(newVerti, newHori);
            }
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();
    }

    public Pair<Integer, Integer> getPlace() {
        return new Pair<Integer, Integer>(this.verti, this.hori);
    }

    public void setPlace(int verti, int hori) {
        this.verti = verti;
        this.hori = hori;
    }
}

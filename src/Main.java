import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    // arguments for sheep(player)
    private static void gameMaster(int verti, int hori) {
        Animal wolf1 = new Animal(0, 1);
        Animal wolf2 = new Animal(0, 3);
        Animal wolf3 = new Animal(0, 5);
        Animal wolf4 = new Animal(0, 7);

        assert(verti == 7);
        Animal sheep = new Animal(verti, hori);
    }
}

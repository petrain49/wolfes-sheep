import javafx.util.Pair;
import items.*;

public class Main {
    public static void main(String[] args) {
        start(7, 2);
    }

    // arguments for sheep(player)
    private static void start(int v, int h) {
        Field field = new Field();

        assert(v == 7);
        Animal sheep = new Animal(v, h, "@");
        Animal wolf1 = new Animal(0, 0, ">");
        Animal wolf2 = new Animal(0, 2, ">");
        Animal wolf3 = new Animal(0, 4, ">");
        Animal wolf4 = new Animal(0, 6, ">");

        field.place(wolf1);
        field.place(wolf2);
        field.place(wolf3);
        field.place(wolf4);
        field.place(sheep);

        System.out.println(field.show());
    }
}

import items.*;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();

        Animal sheep = new Animal(7, 3, "@");

        Animal wolf1 = new Animal(0, 0, ">");
        Animal wolf2 = new Animal(0, 2, ">");
        Animal wolf3 = new Animal(0, 4, ">");
        Animal wolf4 = new Animal(0, 6, ">");

        field.place(wolf1);
        field.place(wolf2);
        field.place(wolf3);
        field.place(wolf4);
        field.place(sheep);

        field.move(sheep, "up", "right");
        field.place(sheep);
        System.out.println(field.show());
    }

    // arguments for sheep(player)
    private static void start(Field field,int v, int h) {
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
    }
}

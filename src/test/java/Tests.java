import items.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void testCloneEquals() {
        AnimalCoord coord = new AnimalCoord((byte) 0, (byte) 0);
        AnimalCoord cloneCoord = coord.clone();

        assertTrue(coord.equals(cloneCoord));
    }

    @Test
    public void testPathToTop() {
        Field field = new Field();

        SheepCoord sheep1 = new SheepCoord((byte) 7, (byte) 3);
        sheep1.place(field);

        SheepCoord sheep2 = new SheepCoord((byte) 0, (byte) 0);

        assertEquals(sheep1.pathToTop(field), 7);
        assertEquals(sheep2.pathToTop(field), 0);
    }

    @Test
    public void testUserMove() {
        Field field = new Field();

        SheepCoord sheep = new SheepCoord((byte) 7, (byte) 3);
        sheep.place(field);

        WolfCoord wolf = new WolfCoord((byte) 6, (byte) 2);
        wolf.place(field);

        assertFalse(sheep.userMove(field, "up", "left"));
        assertTrue(sheep.userMove(field, "up", "right"));
    }
}

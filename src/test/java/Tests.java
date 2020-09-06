import items.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    @Test
    public void testCloneEquals() {
        AnimalCoord coord = new AnimalCoord((byte) 0, (byte) 0);
        AnimalCoord cloneCoord = coord.clone();

        assertTrue(coord.equals(cloneCoord));
    }

    //@Test
    //public void testField
}

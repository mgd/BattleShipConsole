package test;

import com.mgd.BSU;
import com.mgd.Player;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BSUTest {

    private static final ArrayList<ArrayList<String>> CORD_NOT_TAKEN_PLAYER_MAP = new ArrayList<ArrayList<String>>();
    private static final ArrayList<String> GOOD_INPUT_HORZ = new ArrayList<>(Arrays.asList("A1", "A2", "A3"));
    private static final ArrayList<String> GOOD_INPUT_VERT = new ArrayList<>(Arrays.asList("A1", "B1", "C1"));
    private static final ArrayList<String> BAD_INPUT_DIAG = new ArrayList<>(Arrays.asList("A1", "B2", "C3"));
    private static final ArrayList<String> BAD_INPUT_HORZ= new ArrayList<>(Arrays.asList("A1", "A3", "A4"));
    private static final ArrayList<String> BAD_INPUT_VERT = new ArrayList<>(Arrays.asList("A1", "C1", "D1"));
    private static final ArrayList<String> BAD_INPUT_OOB = new ArrayList<>(Arrays.asList("A1", "C1", "Z1"));

    @BeforeClass
    public static void initializePlayerMaps() {

        ArrayList<String> LAST_GOOD_ROW = new ArrayList<>(Arrays.asList("B1", "B2", "B3"));
        CORD_NOT_TAKEN_PLAYER_MAP.add(GOOD_INPUT_HORZ);
        CORD_NOT_TAKEN_PLAYER_MAP.add(LAST_GOOD_ROW);

    }

    @Test
    public void verticalTest() {

        assertTrue(BSU.vertical(GOOD_INPUT_VERT));
        assertFalse(BSU.vertical(BAD_INPUT_VERT));
        assertFalse(BSU.vertical(BAD_INPUT_HORZ));
    }

    @Test
    public void horizontalTest() {

        assertTrue(BSU.horizontal(GOOD_INPUT_HORZ));
        assertFalse(BSU.horizontal(BAD_INPUT_HORZ));
        assertFalse(BSU.horizontal(BAD_INPUT_HORZ));
    }

    @Test
    public void coordsInMapTest() {

        ArrayList<ArrayList<String>> GOOD_MAP = new Player().createMap();
        assertTrue(BSU.coordsInMap(GOOD_INPUT_VERT, GOOD_MAP));
        assertTrue(BSU.coordsInMap(GOOD_INPUT_VERT, GOOD_MAP));
        assertFalse(BSU.coordsInMap(BAD_INPUT_OOB, GOOD_MAP));
    }

    @Test
    public void testCheckAsciRange() {

        assertTrue(BSU.checkAsci(GOOD_INPUT_VERT));
        assertFalse(BSU.checkAsci(BAD_INPUT_OOB));
    }

    @Test
    public void testCoordNotTaken() {

        assertEquals(true, BSU.coordNotTaken(GOOD_INPUT_HORZ));
        assertEquals( false, BSU.coordNotTaken(GOOD_INPUT_VERT));

    }
}

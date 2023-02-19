package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(3);
    }


    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    @DisplayName("Test size after construction")
    public void testSizeAfterConstruction() {
        assertEquals(0, setA.size());
        assertEquals(6, setB.size());
        assertEquals(2, setC.size());
        assertEquals(0, setD.size());
    }


    @Test
    @DisplayName("Test set construction using fromArray")
    public void testConstructionFromArray() {
        assertEquals(2, setC.maxSize());
        assertEquals(2, setC.size());
        assertTrue(setC.contains(50), "fromArray: the element is not in the set");
        assertTrue(setC.contains(60), "fromArray: the element is not in the set");
    }


    @Test
    @DisplayName("Test adding an element")
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        //setB.add(11);  This must throw an exception, so we test it using assertThrows!
        assertThrows(IllegalArgumentException.class, () -> setB.add(11));
        assertFalse(setB.contains(11), "add: added element found in set.");
        assertEquals(6, setB.size(), "add: elements count not as expected.");
    }


    //  There are 3 cases of construction from invalid arrays:
    //  1 - invalid elements
    //  2 - repeated elements
    //  3 - #elements > maxSize

    @Test
    @DisplayName("Test set construction from array with invalid elements")
    public void testAddFromBadArray1() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems));
    }

    @Test
    @DisplayName("Test set construction from array with repeated elements")
    public void testAddFromBadArray2() {
        int[] elems = new int[]{1, 2, 2};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems));
    }

    @Test
    @DisplayName("Test set construction from array with length > maxSize")
    public void testAddFromBadArray3() {
        int[] elems = new int[]{1, 2, 3, 4};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems));
    }


    @Test
    @DisplayName("Test the hashCode of two sets")
    public void testHashCode() {

        //sets with the exact same elements must have the same hashCode
        setD.add(new int[]{50, 60});
        assertTrue(setC.hashCode() == setD.hashCode());

        //sets with differents elements must have different hashCodes
        assertFalse(setB.hashCode() == setC.hashCode());
    }


    @Test
    @DisplayName("Test if a set intersects other set")
    public void testIntersection() {

        // {50, 60} is subset of {10, 20, 30, 40, 50, 60}
        assertTrue(setB.intersects(setC));

        // {10, 20} is not subset of {50, 60}
        setD.add(10);
        setD.add(20);
        assertFalse(setD.intersects(setC)); 
    }


}

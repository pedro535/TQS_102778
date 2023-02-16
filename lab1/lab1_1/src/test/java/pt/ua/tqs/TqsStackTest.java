package pt.ua.tqs;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class TqsStackTest {

    IStack<String> wordsStack;

    @BeforeEach
    @DisplayName("Create a new stack before each test")
    public void setUp() {
        wordsStack = new TqsStack<>();
    }

    @Test
    @DisplayName("Test if stack is empty after creation")
    public void teststackEmptyAfterCreation() {
        assertTrue(wordsStack.isEmpty());
    }

    @Test
    @DisplayName("Test if stack length is 0 after creation")
    public void testStackSizeEqualZero() {
        assertEquals(0, wordsStack.size());
    }

    @Test
    public void testSize(){
        //test empty stack
        assertEquals(0, wordsStack.size());

        //test non-empty stack
        wordsStack.push("Hello");
        assertEquals(1, wordsStack.size());
    }

    @Test
    public void testPush() {
        wordsStack.push("Hello");
        wordsStack.push("World");
        assertEquals(2, wordsStack.size());
    }

    @Test
    public void testPeek() {

        //test empty stack
        assertThrows(NoSuchElementException.class, () -> wordsStack.peek());

        //test non-empty stack
        wordsStack.push("Hello");
        assertEquals("Hello", wordsStack.peek());

    }

    @Test
    public void testPop() {

        //test empty stack
        assertThrows(NoSuchElementException.class, () -> wordsStack.pop());

        //test non-empty stack
        wordsStack.push("Hello");
        wordsStack.push("World");
        assertEquals("World", wordsStack.pop());
        assertEquals(1, wordsStack.size());
    }

    @Test
    public void testIsEmpty() {
        //test empty stack
        assertTrue(wordsStack.isEmpty());

        //test non-empty stack
        wordsStack.push("Hello");
        assertFalse(wordsStack.isEmpty());
    }
    
}

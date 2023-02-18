package pt.ua.tqs;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;


public class TqsStackTest {

    IStack<Integer> stack;

    @BeforeEach
    public void setup() {
        stack = new TqsStack<>();
    }


    @Test
    @DisplayName("Test if the stack is empty on construction")
    public void emptyOnConstruction() {
        assertTrue(stack.isEmpty());
    }


    @Test
    @DisplayName("Test if the size the stack is 0 after construction")
    public void sizeZeroAfterConstruction() {
        assertEquals(0, stack.size());
    }


    @Test
    @DisplayName("Test if stack is not empty and has size equal n, after n pushes")
    public void multiplePushes() {

        //n pushes
        int n = 10;
        for (int i=0; i<n; i++)
            stack.push(i);

        //test if stack is not empty
        assertFalse(stack.isEmpty());

        //test if stack size is equal to n=10
        assertEquals(n, stack.size());
    }

    
    @Test
    @DisplayName("If one pushes x then pops, test if the value popped is x")
    public void popOne() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }


    @Test
    @DisplayName("If one pushes x then peeks, test if the value returned is x and the size stays the same")
    public void peek() {
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.peek());
        assertEquals(2, stack.size()); 
    }


    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    public void multiplePops() {

        //n pushes
        int n = 10;
        for (int i=0; i<n; i++)
            stack.push(i);

        //n pops
        for (int i=0; i<n; i++)
            stack.pop();

        //test if the stack is empty
        assertTrue(stack.isEmpty());

        //test if stack size is 0
        assertEquals(0, stack.size());
    }


    @Test
    @DisplayName("Test if popping from an empty stack does throw a NoSuchElementException")
    public void poppingFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }


    @Test
    @DisplayName("Test if peeking into an empty stack does throw a NoSuchElementException")
    public void peekFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

}

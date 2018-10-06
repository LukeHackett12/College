
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

//-------------------------------------------------------------------------

/**
 * Test class for Doubly Linked List
 *
 * @author
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {
    //~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------

    /**
     * Check if the insertBefore works
     */
    @Test
    public void testIsEmpty() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        assertTrue("Testing is empty on empty list", testDLL.isEmpty());

        testDLL.push(1);
        assertFalse("Testing is empty on empty list", testDLL.isEmpty());
    }

    @Test
    public void testGet() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();

        assertNull("Getting element of empty list", testDLL.getNode(0));

        testDLL.push(1);
        testDLL.push(2);
        testDLL.push(3);
        testDLL.push(4);
        testDLL.push(5);
        testDLL.push(6);
        testDLL.push(7);
        testDLL.push(8);

        assertNull("Got element -2 returned null", testDLL.get(-2));

        int test = testDLL.get(7);
        assertEquals("Got last element", 1, test);

        test = testDLL.get(4);
        assertEquals("Got middle element", 4, test);

        assertNull("Got element past length", testDLL.get(23));
    }

    @Test
    public void testInsertBefore() {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 9);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(3, 3);

        testDLL.insertBefore(2, 4);
        assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "9,2,4,1,3", testDLL.toString());
        testDLL.deleteAt(2);
        testDLL.deleteAt(0);

        testDLL.insertBefore(0, 4);
        assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,2,1,3", testDLL.toString());
        testDLL.insertBefore(1, 5);
        assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,2,1,3", testDLL.toString());
        testDLL.insertBefore(2, 6);
        assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,2,1,3", testDLL.toString());
        testDLL.insertBefore(-1, 7);
        assertEquals("Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,2,1,3", testDLL.toString());
        testDLL.insertBefore(7, 8);
        assertEquals("Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,2,1,3,8", testDLL.toString());
        testDLL.insertBefore(700, 9);
        assertEquals("Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,2,1,3,8,9", testDLL.toString());


        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        assertEquals("Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10, 1);
        assertEquals("Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10, 1);
        assertEquals("Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString());
    }

    @Test
    public void testDelete() {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 3);
        testDLL.insertBefore(0, 4);

        testDLL.deleteAt(2);
        assertEquals("Checking deleteAt to a list containing 4 elements", "4,1,3", testDLL.toString());

        testDLL.insertBefore(1, 5);
        testDLL.deleteAt(3);
        assertEquals("Checking deleteAt to a list containing 4 elements at position 3", "4,5,1", testDLL.toString());

        testDLL.deleteAt(0);
        assertEquals("Checking deleteAt to a list containing 3 elements at position 0", "5,1", testDLL.toString());

        testDLL.deleteAt(-12);
        assertEquals("Checking deleteAt on a minus value on a list with 2 elements", "5,1", testDLL.toString());

        testDLL.deleteAt(0);
        testDLL.deleteAt(0);
        assertEquals("Checking deleteAt list with 1 element", "", testDLL.toString());


        testDLL.push(1);
        testDLL.push(2);
        testDLL.push(3);
        testDLL.push(4);

        testDLL.deleteAt(100);
        assertEquals("Checking deleteAt on a value bigger then the list", "4,3,2,1", testDLL.toString());

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.deleteAt(0);
        assertEquals("Checking deleteAt to an empty list at position 0 - expected nothing in list", "", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.deleteAt(10);
        assertEquals("Checking deleteAt to an empty list at position 10 - expected nothing in list", "", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.deleteAt(-10);
        assertEquals("Checking deleteAt to an empty list at position -10 - expected nothing in list", "", testDLL.toString());
    }

    @Test
    public void testReverse() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(0, 1);

        testDLL.reverse();
        assertEquals("Checking reverse on a list containing 1 element", "1", testDLL.toString());

        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 3);
        testDLL.insertBefore(3, 4);

        testDLL.reverse();
        assertEquals("Checking reverse on a list containing 4 elements", "4,3,2,1", testDLL.toString());

        testDLL = new DoublyLinkedList<>();
        assertEquals("Checking reverse on empty list", "", testDLL.toString());
    }

    @Test
    public void testUnique() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 3);
        testDLL.insertBefore(3, 4);

        testDLL.makeUnique();
        assertEquals("Checking unique to a list containing 4 elements", "1,2,3,4", testDLL.toString());

        testDLL.insertBefore(3, 3);
        testDLL.makeUnique();
        assertEquals("Checking unique to a list containing 4 elements", "1,2,3,4", testDLL.toString());

        testDLL = new DoublyLinkedList<>();
        testDLL.makeUnique();
        assertEquals("Checking unique to a list containing 0 elements", "", testDLL.toString());
    }

    @Test
    public void testStack() {
        DoublyLinkedList<Integer> stack = new DoublyLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals("Checking push 5 elements worked", "5,4,3,2,1", stack.toString());

        int popped = stack.pop();
        assertEquals("Checking pop returned top element", 5, popped);

        stack.pop();
        stack.pop();
        stack.pop();
        assertEquals("Checking pop 3 elements worked", "1", stack.toString());

        stack.pop();
        stack.pop();
        assertEquals("Checking pop on empty list", "", stack.toString());
    }

    @Test
    public void testQueue() {
        DoublyLinkedList<Integer> queue = new DoublyLinkedList<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        assertEquals("Checking enqueue 5 elements worked", "1,2,3,4,5", queue.toString());

        int dequeued = queue.dequeue();
        assertEquals("Checking pop returned top element", 1, dequeued);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("Checking pop 3 elements worked", "5", queue.toString());

        queue.dequeue();
        queue.dequeue();
        assertEquals("Checking pop on empty list", "", queue.toString());
    }
}


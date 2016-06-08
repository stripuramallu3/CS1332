import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class HWTest2 {
    private static final int TIMEOUT = 200;
    private CircularDoublyLinkedList<Integer> list;
    private CircularDoublyLinkedList<String> stringList;
    @Before
    public void setup() {
        list = new CircularDoublyLinkedList<>();
        stringList =  new CircularDoublyLinkedList<>();
    }

    @Test (timeout = TIMEOUT)
    public void testIntegerAdd() {
        for(int i = 0; i <= 1000; i++){
            list.addAtIndex(i, i);
        }
        assertEquals(1001, list.size());
        assertEquals((Integer) 0, list.getHead().getData());
        assertEquals((Integer)1000, list.getHead().getPrevious().getData());
        assertEquals((Integer) 1, list.getHead().getNext().getData());

        list.clear();
        assertEquals(null, list.getHead());

        for(int i = 1000; i >= 0; i--){
            list.addToFront(i);
        }

        assertEquals((Integer) 0, list.getHead().getData());
        assertEquals((Integer) 1000, list.getHead().getPrevious().getData());
        assertEquals((Integer) 1, list.getHead().getNext().getData());

        list.clear();

        for(int i = 0; i <= 1000; i++) {
            list.addAtIndex(list.size(), i);
        }
        assertEquals((Integer) 0, list.getHead().getData());
        assertEquals((Integer) 1000, list.getHead().getPrevious().getData());
        assertEquals((Integer) 1, list.getHead().getNext().getData());

        list.clear();
    }
    @Test (timeout = TIMEOUT)
    public void testStringAdd() {
        for(int i = 0; i <= 1000; i++){
            stringList.addAtIndex(i, "String" + i);
        }
        assertEquals(1001, stringList.size());
        assertEquals("String0", stringList.getHead().getData());
        assertEquals("String1000", stringList.getHead().getPrevious().getData());
        assertEquals("String1", stringList.getHead().getNext().getData());

        stringList.clear();

        for(int i = 1000; i >= 0; i--){
            stringList.addToFront("String" + i);
        }
        assertEquals(1001, stringList.size());
        assertEquals("String0", stringList.getHead().getData());
        assertEquals("String1000", stringList.getHead().getPrevious().getData());
        assertEquals("String1", stringList.getHead().getNext().getData());

        stringList.clear();

        for(int i = 0; i <= 1000; i++) {
            stringList.addAtIndex(stringList.size(), "String" + i);
        }
        assertEquals(1001, stringList.size());
        assertEquals("String0", stringList.getHead().getData());
        assertEquals("String1000", stringList.getHead().getPrevious().getData());
        assertEquals("String1", stringList.getHead().getNext().getData());

        stringList.clear();
    }
    @Test (timeout = TIMEOUT)
    public void testIntegerRemove() {

        for(int i = 0; i <= 1000; i++){
            list.addAtIndex(i, i);
        }
        assertEquals((Integer) 0, list.removeFromFront());
        assertEquals(1000, list.size());
        assertEquals((Integer) 1, list.getHead().getData());
        assertEquals((Integer) 1000, list.getHead().getPrevious().getData());

        list.clear();
        assertEquals(null, list.getHead());

        for(int i = 1000; i >= 0; i--){
            list.addToFront(i);
        }
        assertEquals((Integer)1000, list.removeFromBack());
        assertEquals(1000, list.size());
        assertEquals((Integer) 0, list.getHead().getData());
        assertEquals((Integer) 999, list.getHead().getPrevious().getData());
        assertEquals((Integer) 1, list.getHead().getNext().getData());

        list.clear();

        for(int i = 0; i <= 1000; i++) {
            list.addAtIndex(list.size(), i);
        }
        assertEquals((Integer) 400, list.removeAtIndex(400));
        assertEquals(1000, list.size());
        assertEquals((Integer) 0, list.getHead().getData());
        assertEquals((Integer) 1000, list.getHead().getPrevious().getData());
        assertEquals((Integer)1, list.getHead().getNext().getData());

        list.clear();
    }
    @Test (timeout = TIMEOUT)
    public void testEdgeCaseAdd() {
        list.addAtIndex(0, 0);
        list.addAtIndex(1, 1);
        list.addAtIndex(0, 3);
        list.addAtIndex(3, 4);
        list.addAtIndex(4, 2);

        assertEquals((Integer) 3, list.get(0));
        assertEquals((Integer) 0, list.get(1));
        assertEquals((Integer) 1, list.get(2));
        assertEquals((Integer) 4, list.get(3));
        assertEquals((Integer) 2, list.get(4));

        assertEquals(5, list.size());
    }
    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAndRemove() {
        list.addAtIndex(0, 0);
        list.addAtIndex(1, 1);
        list.addAtIndex(0, 3);
        list.addAtIndex(3, 4);
        list.addAtIndex(4, 2);

        assertEquals((Integer) 3, list.get(0));
        assertEquals((Integer) 0, list.get(1));
        assertEquals((Integer) 1, list.get(2));
        assertEquals((Integer) 4, list.get(3));
        assertEquals((Integer) 2, list.get(4));
        assertEquals(list.getHead(), list.getHead().getNext().getNext().getNext().getNext().getNext());
        assertEquals(list.getHead(), list.getHead().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());
        assertEquals(5, list.size());



        // {3,0,1,4,2}
        assertEquals((Integer) 1, list.removeAtIndex(2));
        // {3,0,4,2}
        assertEquals(list.getHead(), list.getHead().getNext().getNext().getNext().getNext());
        assertEquals(list.getHead(), list.getHead().getPrevious().getPrevious().getPrevious().getPrevious());
        assertEquals(4, list.size());

        assertEquals((Integer) 3, list.removeAtIndex(0));
        // {0,4,2}
        assertEquals(list.getHead(), list.getHead().getNext().getNext().getNext());
        assertEquals(list.getHead(), list.getHead().getPrevious().getPrevious().getPrevious());
        assertEquals(3, list.size());
        assertEquals((Integer) 4, list.removeAtIndex(1));
        // {0,2}
        assertEquals(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(list.getHead(), list.getHead().getPrevious().getPrevious());
        assertEquals(2, list.size());
        assertEquals((Integer) 2, list.removeAtIndex(1));
        // {0}
        assertEquals(list.getHead(), list.getHead().getNext());
        assertEquals(list.getHead(), list.getHead().getPrevious());
        assertEquals(1, list.size());
        assertEquals((Integer) 0, list.removeAtIndex(0));

        assertEquals(null, list.getHead());

        assertEquals(null, list.removeFromBack());
        assertEquals(null, list.removeFromFront());

        list.removeAtIndex(1);
        list.removeAtIndex(0);
        list.removeAtIndex(-1);

    }
    @Test
    public void testStringAndIntegerLargeList() {
        for(int i = 0; i <= 1000; i++) {
            stringList.addAtIndex(i, "String" + i);
        }

        String[] testArray = new String[1001];
        for(int i = 0; i <= 1000; i++) {
            testArray[i] = "String" + i;
        }
        assertArrayEquals(testArray, stringList.toArray());


        assertEquals("String475", stringList.get(475));
        assertEquals("String1000", stringList.getHead().getPrevious().getData());
        assertEquals("String1000", stringList.removeAtIndex(1000));
        assertEquals("String999", stringList.getHead().getPrevious().getData());
        assertEquals("String0", stringList.getHead().getPrevious().getNext().getData());

        for(int i = 0; i <= 1000; i++) {
            list.addAtIndex(i, i);
        }
        assertEquals(1001, list.size());
        for(int i = 1000; i >= 0; i--) {
            assertEquals((Integer) i, list.removeAtIndex(list.size()-1));
        }
        assertEquals(0, list.size());
        for(int i = 0; i <= 1000; i++) {
            list.addAtIndex(list.size(), i);
        }
        assertEquals(1001, list.size());
        for(int i = 0; i <= 1000; i++) {
            assertEquals((Integer) i, list.removeAtIndex(0));
        }
        assertEquals(0, list.size());
    }
    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFirstOccurrence() {
        list.addAtIndex(0,0);
        list.addAtIndex(0,0);
        list.addAtIndex(0,49);
        list.addAtIndex(0,0);
        list.addAtIndex(list.size(),1);
        assertEquals(4, list.removeFirstOccurrence(1));
        list.addToBack(100);
        assertEquals(4, list.removeFirstOccurrence(100));
        assertEquals(1, list.removeFirstOccurrence(49));

        list.removeFirstOccurrence(1234);

        stringList.addAtIndex(0, "First");
        stringList.addAtIndex(1, "Second");
        stringList.addAtIndex(2, "Third");
        stringList.addAtIndex(3, "Fourth");
        assertEquals(3, stringList.removeFirstOccurrence("Fourth"));
        assertEquals(1, stringList.removeFirstOccurrence("Second"));
        assertEquals(0, stringList.removeFirstOccurrence("First"));

        stringList.removeFirstOccurrence("Poop");
    }
    @Test (timeout = TIMEOUT)
    public void testArrays() {
        Integer[] listArray = {0,1,2,3};
        CircularDoublyLinkedList<Integer> test = new CircularDoublyLinkedList<>(listArray);
        assertEquals((Integer) 0, test.get(0));
        assertEquals((Integer) 1, test.get(1));
        assertEquals((Integer) 2, test.get(2));
        assertEquals((Integer) 3, test.get(3));
        assertEquals((Integer) 0, test.getHead().getData());
        assertEquals((Integer) 1, test.removeAtIndex(1));
        Integer[] testReturn = {0,2,3};
        assertArrayEquals(testReturn, test.toArray());
    }
}
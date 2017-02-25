/**
 * Created by Sreerama on 9/5/2015.
 */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tino on 9/4/2015.
 */
public class HW2Test {

    private static final int TIMEOUT = 200;
    private CircularDoublyLinkedList<Integer> list;

    @Before
    public void setup() {
        list = new CircularDoublyLinkedList<>();
    }

    @Test
    public void testAddAndRemove() {
        list.addAtIndex(0, 12);
        assertEquals((Integer) 12, list.getHead().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getPrevious().getData());
        list.removeFromBack();
        assertNull(list.getHead());
        list.clear();

        assertEquals(0, list.size());
        assertNotNull(list);
        assertNull(list.getHead());

        list.addToBack(12);
        assertEquals((Integer) 12, list.getHead().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getPrevious().getData());
        list.removeFromFront();
        assertNull(list.getHead());
        assertEquals(0, list.size());
        assertNotNull(list);

        list.addToFront(12);
        assertEquals((Integer) 12, list.getHead().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getPrevious().getData());
        list.removeAtIndex(0);
        assertNull(list.getHead());
        assertEquals(0, list.size());
        assertNotNull(list);

        list.addToBack(10);
        list.addToFront(11);
        list.addAtIndex(1, 12); // 11, 12, 10

        Integer[] sample = new Integer[] {11, 12, 10};
        assertArrayEquals(sample, list.toArray());

        list.addAtIndex(1, 13);
        list.addAtIndex(1, 12); // 11, 12, 13, 12, 10
        Integer[] sample1 = new Integer[] {11, 13, 12, 10};
        list.removeFirstOccurrence(12);
        assertArrayEquals(sample1, list.toArray());

        Integer[] sample2 = new Integer[] {11, 13, 10};
        list.removeFirstOccurrence(12);
        assertArrayEquals(sample2, list.toArray());

        list.addToFront(11);
        list.addToFront(11);
        list.addToBack(11);
        list.removeAllOccurrences(11);
        Integer[] sample3 = new Integer[] {13, 10};
        assertArrayEquals(sample3, list.toArray());
        assertEquals((Integer) 13, list.getHead().getData());
        list.clear();

        list.addToFront(10);
        list.addToFront(11);
        list.addToFront(12); // 12, 11, 10
        list.removeFromFront();
        assertEquals((Integer) 11, list.getHead().getData());
        assertEquals((Integer) 10, list.getHead().getPrevious().getData());
        assertEquals((Integer) 10, list.getHead().getNext().getData());
        assertEquals((Integer) 11, list.getHead().getNext().getPrevious().getData());
        assertEquals((Integer) 11, list.getHead().getPrevious().getNext().getData());
        list.clear();

        list.addToFront(10);
        list.addToFront(11);
        list.addToFront(12);
        list.removeFromBack();  // 12, 11
        assertEquals((Integer) 12, list.getHead().getData());
        assertEquals((Integer) 11, list.getHead().getPrevious().getData());
        assertEquals((Integer) 11, list.getHead().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getNext().getData());
        list.clear();

        list.addToFront(10);
        list.addToFront(11);
        list.addToFront(12);
        assertEquals((Integer) 11, list.removeAtIndex(1));
        assertEquals((Integer) 12, list.getHead().getData());
        assertEquals((Integer) 10, list.getHead().getPrevious().getData());
        assertEquals((Integer) 10, list.getHead().getNext().getData());
        assertEquals((Integer) 12, list.getHead().getNext().getPrevious().getData());
        assertEquals((Integer) 12, list.getHead().getPrevious().getNext().getData());
        list.clear();

        list.addToFront(1);
        list.addAtIndex(1, 12);
        assertArrayEquals(new Integer[] {1, 12}, list.toArray());
        list.clear();

        assertNull(list.removeFromBack());
        assertNull(list.removeFromFront());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor() {
        Integer[] l = new Integer[] {1, 2, null};
        list = new CircularDoublyLinkedList<>(l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        Integer[] o = null;
        list = new CircularDoublyLinkedList<>(o);
    }
}

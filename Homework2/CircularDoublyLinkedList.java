/**
 * Your implementation of a CircularDoublyLinkedList
 *
 * @author Sreeramamurthy Tripuramallu
 * @version 1.0
 */
import java.util.NoSuchElementException;
public class CircularDoublyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Creates an empty circular doubly-linked list.
     */
    public CircularDoublyLinkedList() {

    }

    /**
     * Creates a circular doubly-linked list with
     * {@code data} added to the list in order.
     * @param data The data to be added to the LinkedList.
     * @throws java.lang.IllegalArgumentException if {@code data} is null or any
     * item in {@code data} is null.
     */
    public CircularDoublyLinkedList(T[] data) {
        if (data == null) {
            throw new IllegalArgumentEception("The data is not valid");
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                throw new IllegalArgumentException("The data is not valid");
            }
        }
        T[] nodes = new T[data.length];
        for (int i = 0; i < data.length; i++) {
            nodes[i] = new LinkedListNode<T>(data[i], null, null);
        }
        head = nodes[0];
        LinkedListNode<T> current = head;
        for (int i = 0; i < nodes.length; i++) {
            if (i = 0) {
                current.setPrevious(nodes[nodes.length - 1]);
                current.setNext(nodes[i + 1]);
            } else if (i = nodes.length - 1) {
                current.setPrevious(nodes[i - 1]);
                current.setNext(head);
            } else {
                current.setPrevious(nodes[i - 1]);
                current.setNext(nodes[i + 1]);
            }
        }
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index not valid");
        } else if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size - 1) {
            addToBack(data);
        } else {
            LinkedListNode<T> c = head;
            for (int i = 0; i < index - 1; i++) {
                c = c.getNext();
            }
            LinkedList<T> node = new LinkedListNode<T>(T data, c, c.getNext());
            c.getNext().setPrevious(node);
            c.setNext(node);
            size++;
        }

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is not valid");
        } else if (index = 0) {
            return head.getData():
        } else if (index == size -1) {
            return head.getPrevious().getData();
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }

    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not valid");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> current = head;
            for(int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            size--;
            return current.getData();
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else if (head == null) {
            head = new LinkedListNode<T>(data, null, null);
            head.setPrevious(head);
            head.setNext(head);
            size++;
        } else {
            LinkedListNode<T> n = head.getPrevious();
            LinkedListNode<T> node = new LinkedListNode<T>(data, head, n);
            head.setPrevious(node);
            head.getPrevious().setNext(node);
            head = node;
            size++;
        }


    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data not valid");
        } else if(head == null) {
            head = new LinkedListNode<T>(data, null, null);
            head.setPrevious(head);
            head.setNext(head);
            size++;
        } else {
            LinkedListNode<T> n = head.getPrevious();
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, n, head);
            head.getPrevious.setNext(newNode);
            head.setPrevious(newNode);
            size++;
        }
    }

    @Override
    public T removeFromFront() {
        LinkedListNode<T> current = head;
        if(head == null || isEmpty()) {
            return null;
        } else if(size == 1) {
            head = null;
        } else {
            head.getPrevious().setNext(head.getNext());
            head.getNext().setPrevious(head.getPrevious());
            head = head.getNext();
        }
        size--;
        return current.getData();


    }

    @Override
    public T removeFromBack() {
        LinkedListNode<T> current = head.getPrevious();
        if (head == null || isEmpty()) {
            return null;
        } else if (size == 1) {
            head = null;
        } else {
            head.setPrevious(current.getPrevious());
            current.getPrevious().setNext(head);
        }
        size--;
        return current.getData();

    }

    @Override
    public int removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < size; i++) {
                if (current.getData().equals(data)) {
                    removeAtIndex(i);
                    return i;
                } else {
                    current = current.getNext();
                }
            }
        }
        throw new NoSuchElementExecption("Data does not exist");

    }

    @Override
    public boolean removeAllOccurrences(T data) {
        //array of indices where linked list equals data
        //for loop the length of that array and removefirstoccerance
        if (data == null) {
            throw new IllegalArgumetnException("Data is not valid");
        } else {
            boolean out = false;
            LinkedListNode<T> current = head;
            int i = 0;
            while (i < size) {
                if (current.getData().isequals(data)) {
                    removeAtIndex(i);
                    i = 0;
                    out = true;
                } else {
                    current = current.getNext();
                }
            }
            return out;
        }

    }

    @Override
    public Object[] toArray() {
        if(isEmpty()) {
            return new Object[0];
        } else {
            Object[] out = new Object[size];
            LinkedListNode<T> current = head;
            for (int i = 0; i < size; i++) {
                out[i] = current;
                current = current.getNext();
            }
            return out;
        }

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /* DO NOT MODIFY THIS METHOD */
    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }
}

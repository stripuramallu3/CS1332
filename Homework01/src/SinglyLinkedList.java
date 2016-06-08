/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Sreeramamurthy Tripuramallu
 * @version 1.0
 */
import java.util.NoSuchElementException;
public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not valid");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> current = head;
            int i = 0;
            while (i < index - 1) {
                current = current.getNext();
                i++;
            }
            LinkedListNode<T> node = current.getNext();
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, node);
            current.setNext(newNode);
            size++;

        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not valid");
        } else if (index == 0) {
            return head.getData();
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not valid");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> current = head;
            int i = 0;
            while (i < index - 1) {
                current = current.getNext();
                i++;
            }
            LinkedListNode<T> removed = current.getNext();
            current.setNext(current.getNext().getNext());
            size--;
            return removed.getData();
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, head);
            head = newNode;
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else if (head == null) {
            addToFront(data);
        } else {
            LinkedListNode<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new LinkedListNode<T>(data));
            size++;
        }

    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        } else {
            LinkedListNode<T> removed = head;
            head = head.getNext();
            size--;
            return removed.getData();
        }
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            return removeFromFront();
        } else {
            LinkedListNode<T> current = head;
            int i = 0;
            while (i < size - 2) {
                current = current.getNext();
                i++;
            }
            LinkedListNode<T> removed = current.getNext();
            current.setNext(null);
            size--;
            return removed.getData();
        }
    }

    @Override
    public int removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is not valid");
        } else {
            if (isEmpty()) {
                throw new NoSuchElementException("Element does not exist");
            } else {
                int i = 0;
                LinkedListNode<T> current = head;
                if (head.getData().equals(data)) {
                    removeFromFront();
                    return i;
                } else {
                    while (i < size) {
                        if (current.getData().equals(data)) {
                            removeAtIndex(i);
                            return i;
                        } else {
                            current = current.getNext();
                            i++;
                        }
                    }
                }
                throw new NoSuchElementException("Element does not exist");
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            newArray[i] = current.getData();
            current = current.getNext();
        }
        return newArray;
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

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}

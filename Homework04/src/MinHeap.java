/**
 * Your implementation of a min heap.
 * @author Sreeramamurthy Tripuramallu
 * @version 1.0
 */
import java.util.NoSuchElementException;
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a Heap.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        backingArray[0] = null;
        size = 0;
    }

    /**
     * returns the index of the parent
     * @param location the index of the item
     * @return the index of its parent
     */
    private int getParent(int location) {
        return (location) / 2;
    }
    /**
     * returns the index of the left child
     * @param location the index of the item
     * @return the index of its left child
     */
    private int getLeft(int location) {
        return 2 * location;
    }

    /**
     * returns the index of the right child
     * @param location the index of the item
     * @return the index of its right child
     */
    private int getRight(int location) {
        return 2 * location + 1;
    }

    /**
     * checks if item has a left child
     * @param location the index of the location
     * @return if the item has a left child
     */
    private boolean hasLeft(int location) {
        return getLeft(location) < size;
    }

    /**
     * checks if item has a right child
     * @param location the index of the item
     * @return if the item has a right child
     */
    private boolean hasRight(int location) {
        return getRight(location) < size;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item not valid");
        } else {
            size++;
            if (size  == backingArray.length) {
                T[] tempArr = (T[]) new Comparable[backingArray.length * 2];
                tempArr[0] = null;
                for (int i = 1; i < backingArray.length; i++) {
                    tempArr[i] = backingArray[i];
                }
                backingArray = tempArr;
            }
            backingArray[size] = item;
            upheap(size);
        }
    }

    /**
     * moves the added item higher, if necessary, to restore the heap property
     * @param j index of the item being moved
     */
    private void upheap(int j) {
        while (j > 1) {
            int p = getParent(j);
            if (backingArray[j].compareTo(backingArray[p]) <= 0) {
                swap(j, p);
            }
            j = p;
        }
    }

    /**
     * swaps two items in the array
     * @param i the index of the first item
     * @param j the index of the second item
     */
    private void swap(int i, int j) {
        T temp = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = temp;
    }


    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element does not exist");
        } else {
            T tempItem = backingArray[1];
            swap(1, size);
            backingArray[size] = null;
            downheap(1);
            size--;
            return tempItem;
        }

    }

    /**
     *
     * @param j the index of the item being moved down
     */
    private void downheap(int j) {
        while (hasLeft(j)) {
            int lI = getLeft(j);
            int sCI = lI;
            if (hasRight(j)) {
                int rightIndex = getRight(j);
                if (backingArray[lI].compareTo(backingArray[rightIndex]) >= 0) {
                    sCI = rightIndex;
                }
            }
            if (backingArray[sCI].compareTo(backingArray[j]) <= 0) {
                swap(j, sCI);
            }
            j = sCI;
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
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    };

    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing array
     */
    public Comparable[] getBackingArray() {
        return backingArray;
    }

}

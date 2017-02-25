/**
 * Your implementation of a min priority queue.
 * @author YOUR NAME HERE
 * @version 1.0
 */
import java.util.NoSuchElementException;
public class MinPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private MinHeap<T> backingHeap;
    // Do not add any more instance variables

    /**
     * Creates a priority queue.
     */
    public MinPriorityQueue() {
        backingHeap = new MinHeap();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Invalid item");
        } else {
            backingHeap.add(item);
        }
    }

    @Override
    public T dequeue() {
        if (backingHeap.isEmpty()) {
            throw new NoSuchElementException("No items to remove");
        }
        return backingHeap.remove();
    }

    @Override
    public boolean isEmpty() {
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap.clear();
    }

    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing heap
     */
    public MinHeap<T> getBackingHeap() {
        return backingHeap;
    }

}

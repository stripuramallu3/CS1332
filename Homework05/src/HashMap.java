import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Your implementation of a HashMap, using external chaining as your collision
 * policy.  Read the PDF for more instructions on external chaining.
 *
 * @author Sreeramamurthy Tripuramallu
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries.
     */
    public HashMap() {
        // Initialize your hashtable here.
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public V add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value not valid");
        } else {
            if (size + 1 > table.length * MAX_LOAD_FACTOR) {
                regrow();
            }
            MapEntry<K, V> entry = new MapEntry(key, value);
            int index = Math.abs(entry.getKey().hashCode()) % table.length;
            V temp = addingHelper(entry, index, table);
            if (temp == null) {
                size++;
            }
            return temp;
        }
    }

    /**
     * Regrows the backing array when the
     * size of the HashMap exceeds the load factor
     */
    private void regrow() {
        int newSize = 2 * table.length + 1;
        MapEntry<K, V>[] tTable = (MapEntry<K, V>[]) new MapEntry[newSize];
        MapEntry<K, V> temp;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                temp = table[i];
                while (temp != null) {
                    MapEntry<K, V> store = temp.getNext();
                    temp.setNext(null);
                    int k = Math.abs(temp.getKey().hashCode()) % tTable.length;
                    addingHelper(temp, k, tTable);
                    temp = store;
                }
            }
        }
        table = tTable;
    }

    /**
     *
     * @param entry the MapEntry being added
     * @param index the index where the entry is being added
     * @param tempTable the table that the entry is being added to
     * @return if there is already a value for a key, then
     * it gets replaced, if not, then null is returned.
     */
    private V addingHelper(MapEntry<K, V> entry,
                           int index, MapEntry<K, V>[] tempTable) {
        if (tempTable[index] == null) {
            tempTable[index] = entry;
        } else {
            MapEntry<K, V> temp = tempTable[index];
            while (temp != null) {
                if (temp.getKey().equals(entry.getKey())) {
                    V tempValue = temp.getValue();
                    temp.setValue(entry.getValue());
                    return tempValue;
                } else if (temp.getNext() == null) {
                    temp.setNext(entry);
                    return null;
                }
                temp = temp.getNext();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key not valid");
        } else if (contains(key)) {
            int index = Math.abs(key.hashCode()) % table.length;
            MapEntry<K, V> temp = table[index];
            if (temp.getKey().equals(key)) {
                table[index] = temp.getNext();
                size--;
                return temp.getValue();

            }
            while (temp.getNext() != null) {
                if (temp.getNext().getKey().equals(key)) {
                    V tempValue = temp.getNext().getValue();
                    temp.setNext(temp.getNext().getNext());
                    size--;
                    return tempValue;
                } else {
                    temp = temp.getNext();
                }
            }

        }
        throw new NoSuchElementException("Key does not exist");

    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key not valid");
        } else if (contains(key)) {
            int index = Math.abs(key.hashCode()) % table.length;
            MapEntry<K, V> temp = table[index];
            while (temp != null) {
                if (temp.getKey().equals(key)) {
                    return temp.getValue();
                } else {
                    temp = temp.getNext();
                }
            }
        }
        throw new NoSuchElementException("Key does not exist");
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is not valid");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> temp = table[index];
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                return true;
            } else {
                temp = temp.getNext();
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;

    }

    @Override
    public int size() {
        return size;

    }

    @Override
    public Set<K> keySet() {
        Set<K> output = new HashSet();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> temp = table[i];
            while (temp != null) {
                output.add(temp.getKey());
                temp = temp.getNext();
            }
        }
        return output;
    }

    @Override
    public List<V> values() {
        List values = new ArrayList();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> temp = table[i];
            while (temp != null) {
                values.add(temp.getValue());
                temp = temp.getNext();
            }
        }
        return values;

    }

    /**
     * DO NOT USE THIS METHOD IN YOUR CODE.  IT IS FOR TESTING ONLY
     * @return the backing array of the data structure, not a copy.
     */
    public MapEntry<K, V>[] toArray() {
        return table;
    }

}
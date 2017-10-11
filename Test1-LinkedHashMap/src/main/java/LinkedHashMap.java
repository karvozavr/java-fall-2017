import java.util.*;


/**
 * Linked hashMap class
 * @param <K> key type
 * @param <V> value type
 */
public class LinkedHashMap<K extends Comparable<K>, V extends Comparable<V>> extends AbstractMap<K, V> {

    private int size;

    private int capacity;

    private final int averageListCapacity = 10;

    private final int initialCapacity = 10;

    private LinkedList entries;

    private LinkedList[] table;

    LinkedHashMap() {
        table = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
        }
        size = 0;
        capacity = initialCapacity;
    }

    /**
     * Linked HashMap entry
     * @param <EntryK> key type
     * @param <EntryV> value type
     */
    private static class LinkedHashMapEntry<EntryK extends Comparable<EntryK>, EntryV extends Comparable<EntryV>> implements Entry<EntryK, EntryV> {

        private final EntryK key;
        private EntryV value;

        LinkedHashMapEntry(EntryK key, EntryV value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public EntryK getKey() {
            return key;
        }

        @Override
        public EntryV getValue() {
            return value;
        }

        @Override
        public EntryV setValue(EntryV value) {
            EntryV oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }


    /**
     * put new entry to HashTable
     * @param key key
     * @param value value
     * @return
     */
    @Override
    public V put(K key, V value) {
        Entry<K, V> entry = findEntryInList(key, table[getIndex(key)]);
        if (entry != null) {
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        } else {
            if (size * averageListCapacity == capacity) {
                rehash();
            }
            Entry newEntry = new LinkedHashMapEntry<K, V>(key, value);
            table[getIndex(key)].push(newEntry);
            entries.push(newEntry);
            size++;
            return null;
        }
    }

    public void clear() {
        table = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
        }

        size = 0;
    }

    private void rehash() {
        capacity *= 2;
        LinkedList[] oldTable = table;
        clear();

        for (LinkedList<Entry<K, V>> list : oldTable) {
            for (Entry<K, V> entry : list) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }


    @Override
    public V get(Object key) {
        Entry<K, V> entry = findEntryInList(key, table[getIndex(key)]);
        if (entry != null) {
            return entry.getValue();
        } else {
            return null;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return findEntryInList(key, table[getIndex(key)]) != null;
    }

    private Entry<K, V> findEntryInList(Object key, LinkedList<Entry<K, V>> list) {
        for (Entry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
        }

        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return entries.iterator();
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(Object key) {
        return Math.abs(key.hashCode() % table.length);
    }
}

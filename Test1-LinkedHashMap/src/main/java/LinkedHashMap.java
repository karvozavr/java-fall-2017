import java.security.KeyStore;
import java.util.*;

public class LinkedHashMap<K extends Comparable<K>, V extends Comparable<V>> extends AbstractMap<K, V> {

    /**
     * @value количество ключей в хеш-таблице
     */
    private int size;

    /**
     * @value количество списков в хеш-таблице
     */
    private int capacity;

    /**
     * @value средняя емкость списка хеш-таблицы
     * при достижении размера хеш-таблицы суммарной средней емкости списков производится расширение
     */
    private final int averageListCapacity = 10;

    private final int initialCapacity = 10;

    private LinkedList entries;

    /**
     * @value Массив списков хеш-таблицы
     */
    private LinkedList[] table;

    LinkedHashMap() {
        table = new LinkedList[capacity];
        size = 0;
        capacity = initialCapacity;
    }

    private static class LinkedHashMapEntry<EntryK extends Comparable<EntryK>, EntryV extends Comparable<EntryV>> implements Entry<EntryK, EntryV> {

        private EntryK key;
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


    @Override
    public V put(K key, V value) {
        Entry<K,V> entry = findEntryInList(key, table[getIndex(key)]);
        if (entry != null) {
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        } else {
            Entry newEntry = new LinkedHashMapEntry<K, V>(key, value);
            table[getIndex(key)].push(newEntry);
            entries.push(newEntry);
            return null;
        }
    }

    @Override
    public V get(Object key) {
        Entry<K,V> entry = findEntryInList(key, table[getIndex(key)]);
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
                return null;
            }

            @Override
            public int size() {
                return 0;
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

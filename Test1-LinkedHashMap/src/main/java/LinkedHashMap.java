import java.util.*;

public class LinkedHashMap<K, V> extends AbstractMap<K, V> {

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

    /**
     * @value Массив списков хеш-таблицы
     */
    private LinkedList[] table;

    LinkedHashMap() {
        table = new LinkedList[capacity];
        size = 0;
        capacity = initialCapacity;
    }

    public V put(K key, V value) {
        Entry<K,V> entry = findEntryInList(key, table[getIndex(key)]);
        if (entry != null) {
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        } else {

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
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(Object key) {
        return Math.abs(key.hashCode() % table.length);
    }
}

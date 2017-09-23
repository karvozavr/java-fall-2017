package ru.spbau.mit.java.fall2017.containers;

/**
 * расширяющаяся хеш-таблица, реализованная на списках
 */
public class HashTable implements Cloneable {

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

    /**
     * @value Массив списков хеш-таблицы
     */
    private LinkedList[] table;

    public HashTable() {
        size = 0;
        capacity = 2;
        table = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
        }
    }

    /**
     * getter
     * @return количество ключей в хеш-таблице
     */
    public int size() {
        return size;
    }

    /**
     * проверить, содержится ли ключ в хеш-таблице
     * @param key ключ для поиска в хеш-таблице
     * @return true, если данный ключ содержится в хеш-таблице, иначе - false
     */
    public boolean contains(String key) {
        return get(key) != null;
    }

    /**
     * Найти значение по ключу в хеш-таблице
     * @param key ключ для поиска в хеш-таблице
     * @return значение по ключу, или null, если такого значения нет
     */
    public String get(String key) {
        return table[indexByKey(key)].getByKey(key);
    }

    /**
     * положить в хеш-таблицу значение value по ключу key
     * @param key ключ для добавления в хеш-таблицу
     * @param value значение для добавления в хеш-таблицу
     * @return то, что было по данному ключу раньше, либо null, если ничего не было
     */
    public String put(String key, String value) {
        if (size == capacity * averageListCapacity) {
            resize();
        }

        LinkedList l = table[indexByKey(key)];
        String oldValue = table[indexByKey(key)].add(key, value);
        if (oldValue != null) {
            ++size;
            return oldValue;
        } else {
            return null;
        }
    }

    /**
     * удалить значение по заданному ключу из хеш-таблицы
     * @param key люч для удаления из хеш-таблицы
     * @return удалённое значение, либо null, если такого значения не было
     */
    public String remove(String key) {
        return table[indexByKey(key)].removeByKey(key);
    }

    /**
     * очистить хеш-таблицу
     */
    public void clear() {
        table = new LinkedList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
        }
    }

    private int indexByKey(String key) {
        return key.hashCode() % capacity;
    }

    /**
     * увеличить количество списков в хеш-таблице вдвое, произвести рехеширование
     */
    private void resize() {
        capacity *= 2;
        LinkedList[] oldTable = table;
        clear();

        for (LinkedList list : oldTable) {
            for (String key : list) {
                put(key, oldTable[indexByKey(key)].getByKey(key));
                oldTable[indexByKey(key)].removeByKey(key);
            }
        }
    }
}

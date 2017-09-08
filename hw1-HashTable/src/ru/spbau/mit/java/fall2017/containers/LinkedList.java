package ru.spbau.mit.java.fall2017.containers;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Связный список для хранения пар "ключ-значение".
 */
public class LinkedList implements Iterable<String> {
    private Node head;
    private Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Добавить элемент - пару "ключ-значение" в список.
     *
     * @param key   ключ добавляемой пары
     * @param value значение добавляемой пары
     * @return вернуть то, что было по этому ключу раньше, либо null
     */
    public String add(String key, String value) {
        String oldValue = updateByKey(key, value);
        if (oldValue == null) {
            if (head == null) {
                head = new Node(key, value);
                tail = head;
            } else {
                Node new_tail = new Node(key, value, null, tail);
                tail.setNext(new_tail);
                tail = new_tail;
            }
        }

        return oldValue;
    }

    /**
     * Попытаться обновить значение, если элемент с данным ключом уже есть.
     *
     * @param key   ключ добавляемой пары
     * @param value значение добавляемой пары
     * @return вернуть то, что было по этому ключу раньше, либо null
     */
    private String updateByKey(String key, String value) {
        Node node = head;

        while (node != null) {
            if (key.equals(node.getKey())) {
                String oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.getNext();
        }

        return null;
    }

    /**
     * Найти элемент по ключу в списке.
     *
     * @param key ключ добавляемого элемента
     * @return вернуть то, что было по этому ключу раньше, либо null
     */
    public String getByKey(String key) {
        Node node = head;

        while (node != null) {
            if (key.equals(node.getKey())) {
                return node.getValue();
            }
            node = node.getNext();
        }

        return null;
    }

    /**
     * Удалить элемент по ключу из списка.
     *
     * @param key ключ удаляемого элемента
     * @return вернуть то, что было по этому ключу раньше, либо null
     */
    public String removeByKey(String key) {
        Node node = head;

        while (node != null) {
            if (key.equals(node.getKey())) {
                if (node.getNext() != null)
                    node.getNext().setPrev(node.getPrev());

                if (node.getPrev() != null)
                    node.getPrev().setNext(node.getNext());

                if (node == head)
                    head = head.next;

                if (node == tail)
                    tail = tail.getPrev();


                return node.getValue();
            }
            node = node.next;
        }

        return null;
    }

    @Override
    public Iterator<String> iterator() {
        return new LinkedListIterator(this);
    }

    public class LinkedListIterator implements Iterator<String> {

        private Node currentNode;

        public LinkedListIterator(LinkedList linkedList) {
            this.currentNode = linkedList.head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public String next() {
            if (hasNext()) {
                Node prev = currentNode;
                currentNode = currentNode.getNext();
                return prev.getKey();
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * List node.
     */
    private class Node {
        private Node next;
        private Node prev;

        private String key;
        private String value;

        Node(String key, String value) {
            this(key, value, null, null);
        }

        Node(String key, String value, Node next, Node prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}



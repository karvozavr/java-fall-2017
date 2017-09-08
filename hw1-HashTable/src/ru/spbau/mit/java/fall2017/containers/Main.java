package ru.spbau.mit.java.fall2017.containers;

public class Main {
    public static void main(String[] args) {
        HashTable table = new HashTable();
        table.put("lol", "value1");
        table.put("kek", "val2");
        table.put("kek", "val2");
        table.put("kek", "vallol");
        table.put("cheburek", "val3");
        System.out.println(table.get("kek"));
        System.out.println(table.get("lol"));
        System.out.println(table.get("cheburek"));
        table.remove("kek");
        table.remove("lol");
        table.remove("cheburek");
        System.out.println(table.get("kek"));
        table.put("lol", "val1");
        table.put("kek", "val2");
        table.put("cheburek", "val3");
        System.out.println(table.get("kek"));
        System.out.println(table.get("lol"));
        System.out.println(table.get("cheburek"));
        table.clear();
        System.out.println(table.get("kek"));
        System.out.println(table.get("lol"));
        System.out.println(table.get("cheburek"));
    }
}

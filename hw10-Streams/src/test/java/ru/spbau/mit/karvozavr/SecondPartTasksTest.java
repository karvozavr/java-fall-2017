package ru.spbau.mit.karvozavr;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;

public class SecondPartTasksTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void testFindQuotes() throws IOException {
        File tempFile1 = testFolder.newFile("file.txt");
        List<String> strings1 = Arrays.asList("This contains substring. 1\n", "This doesn't 1\n", "Some trash 1\n", "Onemorestringwithsubstringinit 1\n");
        List<String> strings2 = Arrays.asList("This contains substring. 2\n", "This doesn't 2\n", "Some trash 2\n", "Onemorestringwithsubstringinit 2\n");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile1))) {
            for (String s : strings1) {
                writer.write(s);
            }
        }

        File tempFile2 = testFolder.newFile("file2.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile2))) {
            for (String s : strings2) {
                writer.write(s);
            }
        }

        List<String> lines = SecondPartTasks.findQuotes(Arrays.asList(tempFile1.getAbsolutePath(), tempFile2.getAbsolutePath()), "substring");


        assertThat(lines.size(), is(4));
        assertThat(lines, containsInAnyOrder("This contains substring. 1",
                "Onemorestringwithsubstringinit 1",
                "This contains substring. 2",
                "Onemorestringwithsubstringinit 2"));
    }

    @Test
    public void testPiDividedBy4() {
        for (int i = 0; i < 10; ++i) {
            assertTrue(Math.abs(SecondPartTasks.piDividedBy4() - Math.PI / 4) < 0.01);
        }
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> compositions = new HashMap<>();
        List<String> author1 = Arrays.asList("Война и мир", "Анна Каренина");
        List<String> author2 = Arrays.asList("Леди Макбет Мценского уезда", "Солнечный удар");
        List<String> author3 = Arrays.asList("Отцы и дети", "Ася", "Муму", "Дворянское гнездо");
        List<String> author4 = Arrays.asList("Идиот", "Игрок", "Бесы", "Братья Карамазовы");
        compositions.put("Лев Толстой", author1);
        compositions.put("Иван Бунин", author2);
        compositions.put("Иван Тургенев", author3);
        compositions.put("Федор Достоевский", author4);

        assertEquals("Иван Бунин", SecondPartTasks.findPrinter(compositions));
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> answer = new HashMap<>();
        answer.put("Book", 200);
        answer.put("Ammo", 100);

        Map<String, Integer> test1 = new HashMap<>();
        test1.put("Book", 42);
        test1.put("Ammo", 0);

        Map<String, Integer> test2 = new HashMap<>();
        test2.put("Book", 8);
        test2.put("Ammo", 40);

        Map<String, Integer> test3 = new HashMap<>();
        test3.put("Book", 150);
        test3.put("Ammo", 60);

        List<Map<String, Integer>> list = Arrays.asList(test1, test2, test3);
        assertEquals(answer, SecondPartTasks.calculateGlobalOrder(list));
    }
}
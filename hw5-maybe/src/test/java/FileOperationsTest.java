import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileOperationsTest {

    @Test
    void testSquareNumbersBase() {
        String data[] = {"1", " ", "22", "", "12", "aaa", "12kele", "12___0", "lol", "2212312"};
        squareNumbersHelper(data);
    }

    @Test
    void testSquareNumbersSmoke() {
        String data[] = {"1", "22", "12", "aaaaa"};
        squareNumbersHelper(data);
    }

    private void squareNumbersHelper(String data[]) {
        String inFile = "file.in";
        String outFile = "file.out";

        try (PrintStream outStream = new PrintStream(inFile)) {
            for (String s : data) {
                outStream.println(s);
            }
        } catch (FileNotFoundException e) {
            fail(e);
        }

        Object numbers[] = Arrays.stream(data)
                .map(s -> {
                    try {
                        int value = Integer.parseInt(s);
                        return Integer.toString(value * value);
                    } catch (NumberFormatException e) {
                        return "null";
                    }
                }).toArray();

        FileOperations.squareNumbers(inFile, outFile);

        try (Stream<String> lines = Files.lines(Paths.get(outFile))) {
            assertArrayEquals(numbers, lines.toArray());
        } catch (IOException e) {
            fail(e);
        }
        try {
            Files.deleteIfExists(Paths.get(inFile));
            Files.deleteIfExists(Paths.get(outFile));
        } catch (IOException e) {
            fail(e);
        }
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get("file.in"));
        Files.deleteIfExists(Paths.get("file.out"));
    }

}
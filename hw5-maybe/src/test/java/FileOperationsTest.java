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
    void testSquareNumbersSmoke() {
        String data[] = {"1", "22", "12", "aaaaa"};
        squareNumbersHelper(data);
    }

    @Test
    void testSquareNumbersBase() {
        String data[] = {"1", "\n", " ", "22", "", "12", "aaaaa", "12keke", "12___0", "lol", "00000002212312"};
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
                .filter(s -> {
                    try {
                        int value = Integer.parseInt(s);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .map(s -> {
                    try {
                        int value = Integer.parseInt(s);
                        return Integer.toString(value * value);
                    } catch (NumberFormatException e) {
                        return false;
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

}
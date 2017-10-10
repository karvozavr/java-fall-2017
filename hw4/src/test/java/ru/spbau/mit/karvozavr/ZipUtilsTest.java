package ru.spbau.mit.karvozavr;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

class ZipUtilsTest {

    private static Path workingDirectory;
    private static String workingDirectoryName;

    static {
        workingDirectoryName = "workingDir";
        workingDirectory = Paths.get(workingDirectoryName);
    }

    /**
     * Test how files being extracted from one archive with recursive structure.
     *
     * @throws IOException which should not be thrown
     */
    @Test
    void testExtractByRegexFromArchiveCorrectness() throws IOException {
        Files.createDirectory(workingDirectory);

        ZipUtils.extractByRegexFromArchive(Paths.get("src", "test", "resources", "test1.zip"), ".*f.*", workingDirectory);

        try (final Stream<Path> directory = Files.walk(Paths.get("src", "test", "resources", "reference", "test1"))) {
            directory
                    .filter(file -> !Files.isDirectory(file))
                    .forEach(file -> assertTrue(Files.exists(Paths.get(workingDirectoryName, file.getFileName().toString()))));
        }


        try (final Stream<Path> directory = Files.walk(workingDirectory)) {
            directory
                    .filter(file -> !Files.isDirectory(file))
                    .forEach(file -> {
                        try {
                            assertTrue(FileUtils.contentEquals(file.toFile(),
                                    Paths.get("src", "test", "resources", "reference", "test1", file.getFileName().toString()).toFile()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            fail("Everything gone bad.");
                        }
                    });
        }
    }

    /**
     * Same as above, but with directory with zip files.
     *
     * @throws IOException which should not be thrown
     */
    @Test
    void testExtractByRegex() throws IOException {
        Files.createDirectory(workingDirectory);

        ZipUtils.extractByRegex(Paths.get("src", "test", "resources").toString(), ".*f.*", workingDirectoryName);

        try (final Stream<Path> directory = Files.walk(Paths.get("src", "test", "resources", "reference", "test2"))) {
            directory
                    .filter(file -> !Files.isDirectory(file))
                    .forEach(file -> assertTrue(Files.exists(Paths.get(workingDirectoryName, file.getFileName().toString()))));
        }


        try (final Stream<Path> directory = Files.walk(workingDirectory)) {
            directory
                    .filter(file -> !Files.isDirectory(file))
                    .forEach(file -> {
                        try {
                            assertTrue(FileUtils.contentEquals(file.toFile(),
                                    Paths.get("src", "test", "resources", "reference", "test2", file.getFileName().toString()).toFile()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            fail("Everything gone bad.");
                        }
                    });
        }
    }

    @AfterEach
    void afterClean() {
        try (final Stream<Path> directory = Files.walk(workingDirectory)) {
            directory
                    .sorted(Comparator.reverseOrder())
                    .forEach(file -> {
                        try {
                            Files.deleteIfExists(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
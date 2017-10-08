package ru.spbau.mit.karvozavr;

import java.io.IOException;
import java.nio.file.*;

public class ZipUtils {

    /**
     * Extract all files that match regexp from zip archive.
     * @param zipFileName path to archive
     * @param regex regular expression to match
     * @throws IOException in case of some troubles with files
     */
    public static void extractByRegex(String zipFileName, String regex) throws IOException {
        Path zipFile = Paths.get(zipFileName);
        try (final FileSystem fileSystem = FileSystems.newFileSystem(zipFile, null)) {
            PathMatcher matcher = fileSystem.getPathMatcher("regex:" + regex);

            Files.walk(fileSystem.getRootDirectories().iterator().next())
                    .filter(file -> !Files.isDirectory(file))
                    .filter(matcher::matches)
                    .forEach(file -> {
                        try {
                            Files.copy(file, Paths.get(file.getFileName().toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}

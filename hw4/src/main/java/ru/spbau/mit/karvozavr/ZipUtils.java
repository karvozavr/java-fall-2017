package ru.spbau.mit.karvozavr;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class ZipUtils {

    /**
     * Extract all files that match regexp from given zip archive.
     *
     * @param zipFile path to archive
     * @param regex   regular expression to match
     * @throws IOException in case of some troubles with files
     */
    public static void extractByRegexFromArchive(Path zipFile, String regex, Path baseDirectory) throws IOException {
        try (final FileSystem fileSystem = FileSystems.newFileSystem(zipFile, null)) {
            PathMatcher matcher = fileSystem.getPathMatcher("regex:" + regex);

            try (final Stream<Path> archiveRoot = Files.walk(fileSystem.getRootDirectories().iterator().next())) {
                archiveRoot.filter(file -> !Files.isDirectory(file))
                        .filter(file -> matcher.matches(file.getFileName()))
                        .forEach(file -> {
                            try {
                                Files.copy(file, Paths.get(baseDirectory.getFileName().toString(), file.getFileName().toString()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
        }
    }

    /**
     * Extract all files that match regexp from all zip archives in given directory.
     *
     * @param path  directory to work in
     * @param regex regular expression to match
     * @throws IOException in case of some troubles with files
     */
    public static void extractByRegex(String path, String regex, String baseDirectoryName) throws IOException {
        try (final Stream<Path> directory = Files.walk(Paths.get(path))) {
            directory
                    .filter(file -> !Files.isDirectory(file))
                    .filter(file -> file.getFileName().toString().endsWith(".zip"))
                    .forEach(archive -> {
                        try {
                            extractByRegexFromArchive(archive, regex, Paths.get(baseDirectoryName));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}

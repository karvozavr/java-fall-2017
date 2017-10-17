import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.java.karvozavr.maybe.Maybe;
import ru.spbau.mit.java.karvozavr.maybe.exception.MaybeIsNothingException;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileOperations {

    /**
     * Get all the numbers from the input file and save them squared to the output file.
     *
     * @param inputFileName  input file name
     * @param outputFileName output file name
     */
    public static void squareNumbers(@NotNull String inputFileName, @NotNull String outputFileName) {
        try (Stream<String> lines = Files.lines(Paths.get(inputFileName));
             PrintStream outStream = new PrintStream(outputFileName)) {
            lines
                    .map(FileOperations::parseIntToMaybe)
                    .map(x -> x.map(a -> a * a))
                    .forEach(x -> {
                        try {
                            if (x.isPresent()) {
                                outStream.println(x.get());
                            } else {
                                outStream.println("null");
                            }
                        } catch (MaybeIsNothingException e) {
                            System.err.println("There has to be no exceptions!");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse int to Maybe
     *
     * @param s input string
     * @return parsed Just value or Nothing
     */
    @NotNull
    private static Maybe<Integer> parseIntToMaybe(@NotNull String s) {
        Integer value;
        try {
            value = Integer.parseInt(s);
            return Maybe.just(value);
        } catch (NumberFormatException e) {
            return Maybe.nothing();
        }
    }
}

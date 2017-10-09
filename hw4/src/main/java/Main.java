import ru.spbau.mit.karvozavr.ZipUtils;

import java.io.IOException;

public class Main {

    /**
     * main function
     * @param args first argument must be path to archive, second - regexp
     */
    public static void main(String[] args) throws IOException {
        try {
            ZipUtils.extractByRegex(args[0], args[1]);
        } catch (IOException e) {
            System.err.println("Something has terribly crashed in IO.");
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid arguments!");
        }
    }
}

import ru.spbau.mit.karvozavr.ZipUtils;

import java.io.IOException;

public class Main {

    /**
     * main function
     * @param args first argument must be path to archive, second - regexp
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new RuntimeException("Invalid arguments!");
        }

        try {
            ZipUtils.extractByRegex(args[0], args[1], System.getProperty("user.dir"));
        } catch (IOException e) {
            System.err.println("Something has terribly crashed in IO.");
            throw e;
        }
    }
}

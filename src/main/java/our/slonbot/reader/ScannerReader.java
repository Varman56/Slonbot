package our.slonbot.reader;

import java.util.Scanner;

public class ScannerReader implements IReader {
    private final Scanner scanner;

    public ScannerReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public void close() {
        scanner.close();
    }
}

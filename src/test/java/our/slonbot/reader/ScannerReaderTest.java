package our.slonbot.reader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ScannerReaderTest {

    private final InputStream originalSystemIn = System.in;
    private ScannerReader scannerReader;

    @BeforeEach
    void setUp() {
        // Set up ScannerReader with an empty input stream by default
        setInputStream("");
        scannerReader = new ScannerReader();
    }

    @AfterEach
    void tearDown() {
        // Restore original System.in
        System.setIn(originalSystemIn);
        // Close the scanner to release resources
        scannerReader.close();
    }

    private void setInputStream(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    @Test
    void readLine_readsCorrectLine() {
        String testInput = "Hello, world!";
        setInputStream(testInput);
        scannerReader = new ScannerReader(); // Reinitialize with new input stream

        String readLine = scannerReader.readLine();
        assertEquals(testInput, readLine);
    }

    @Test
    void hasNextLine_returnsTrueForAvailableLine() {
        String testInput = "First line\nSecond line";
        setInputStream(testInput);
        scannerReader = new ScannerReader();

        assertTrue(scannerReader.hasNextLine());
        scannerReader.readLine(); // Consume the first line
        assertTrue(scannerReader.hasNextLine());
    }

    @Test
    void hasNextLine_returnsFalseForNoAvailableLine() {
        String testInput = "Single line";
        setInputStream(testInput);
        scannerReader = new ScannerReader();

        scannerReader.readLine(); // Consume the single line
        assertFalse(scannerReader.hasNextLine());
    }

    @Test
    void close_closesScannerWithoutErrors() {
        // No direct way to assert Scanner is closed, but we can check for no exceptions
        // The tearDown method already calls close, so we just ensure it runs without issues.
    }
}

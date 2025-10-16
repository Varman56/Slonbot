package our.slonbot.reader;

public interface IReader {
    String readLine();
    boolean hasNextLine();
    void close();
}

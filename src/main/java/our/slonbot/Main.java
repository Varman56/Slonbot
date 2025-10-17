package our.slonbot;

import our.slonbot.connection.IDataWorker;
import our.slonbot.connection.InMemoryDataWorker;
import our.slonbot.presentation.controller.Controller;
import our.slonbot.presentation.view.ConsoleView;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;
import our.slonbot.reader.ScannerReader;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        String charsetOut = System.out.charset().displayName();
        if (!"UTF-8".equals(charsetOut)) {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        }

        IReader reader = new ScannerReader();
        IView view = new ConsoleView();
        IDataWorker dataWorker = new InMemoryDataWorker();
        Controller controller = new Controller(reader, view, dataWorker);

        controller.start();
    }
}
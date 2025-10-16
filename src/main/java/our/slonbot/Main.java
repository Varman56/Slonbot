package our.slonbot;

import our.slonbot.connection.IDataWorker;
import our.slonbot.connection.InMemoryDataWorker;
import our.slonbot.presentation.controller.Controller;
import our.slonbot.presentation.view.ConsoleView;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;
import our.slonbot.reader.ScannerReader;

public class Main {
    public static void main(String[] args) {
        IReader reader = new ScannerReader();
        IView view = new ConsoleView();
        IDataWorker dataWorker = new InMemoryDataWorker();
        Controller controller = new Controller(reader, view, dataWorker);

        controller.start();
    }
}
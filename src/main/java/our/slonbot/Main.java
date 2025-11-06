package our.slonbot;

import our.slonbot.connection.HibernateDataManager;
import our.slonbot.connection.IDataManager;
import our.slonbot.presentation.controller.Controller;
import our.slonbot.presentation.view.ConsoleView;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;
import our.slonbot.reader.ScannerReader;
import our.slonbot.worker.IWorker;
import our.slonbot.worker.Worker;
import our.slonbot.connection.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();

        IReader reader = new ScannerReader();
        IView view = new ConsoleView();
        IDataManager dataManager = new HibernateDataManager();
        IWorker worker = new Worker(dataManager);

        Controller controller = new Controller(reader, view, worker);

        controller.start();
    }
}
package our.slonbot;

import org.sqlite.SQLiteException;
import our.slonbot.connection.IDataManager;
import our.slonbot.connection.SQLQueries;
import our.slonbot.connection.SQLiteManager;
import our.slonbot.presentation.controller.Controller;
import our.slonbot.presentation.view.ConsoleView;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;
import our.slonbot.reader.ScannerReader;
import our.slonbot.worker.IWorker;
import our.slonbot.worker.Worker;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        IReader reader = new ScannerReader();
        IView view = new ConsoleView();
        try(IDataManager dataManager = new SQLiteManager()) {
            IWorker worker = new Worker(dataManager);
        } catch (Exception e) {
            //log
        };


        Controller controller = new Controller(reader, view, worker);

        controller.start();
    }
}
package our.slonbot.presentation.controller;

import our.slonbot.connection.IDataWorker;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;

public class Controller {

    private IView view;
    private IReader reader;
    private IDataWorker database;

    public Controller(IReader reader, IView view, IDataWorker database) {
        this.view = view;
        this.reader = reader;
        this.database = database;
    }

    public void start() {
        view.showWelcome();
        mainCycle:
        while (true) {
            view.showHelp();
            switch (reader.readLine()) {
                case "eat" -> onEatingRequest();
                case "work" -> onWorkRequest();
                case "stat" -> onStatRequest();
                case "help" -> view.showHelp();
                default -> {
                    break mainCycle;
                }
            }
        }
        reader.close();
    }

    void onEatingRequest(){

    }

    void onWorkRequest(){

    }

    void onStatRequest() {

    }


}

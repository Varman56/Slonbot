package our.slonbot.presentation.controller;

import our.slonbot.model.AppType;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;
import our.slonbot.presentation.TextConstants;
import our.slonbot.worker.IWorker;

public class Controller {

    private final IView view;
    private final IReader reader;
    private final IWorker worker;

    public Controller(IReader reader, IView view, IWorker worker) {
        this.view = view;
        this.reader = reader;
        this.worker = worker;
    }

    public void start() {
        view.showWelcome();
        AppType appType = AppType.Console; // It will handler route
        long playerId = 0; // It will handler route
        while (true) {
            var command = reader.readLine();
            onCommand(playerId, appType, command);
        }
    }

    public void onCommand(long playerId, AppType appType, String command) {
        String[] args = command.split(" ");
        if (args.length == 0) {
            return;
        }

        switch (args[0]) {
            case "eat" -> handleEatCommand(appType, playerId, args);
            case "work" -> handleWorkCommand(appType, playerId, args);
            case "stat" -> handleStatCommand(appType, playerId);
            case "help" -> view.showHelp();
            default -> onUnknownCommand();
        }
    }

    private void handleEatCommand(AppType appType, long playerId, String[] args) {
        switch (args.length) {
            case 1 -> view.showFood(worker.getAllFoodInfo());
            case 2 -> {
                String result = worker.onEatingRequest(appType, playerId, args[1]);
                view.showAdditional(result);
            }
            default -> onUnknownCommand();
        }
    }

    private void handleWorkCommand(AppType appType, long playerId, String[] args) {
        switch (args.length) {
            case 1 -> view.showWork(worker.getAllWorkInfo());
            case 2 -> {
                String result = worker.onWorkRequest(appType, playerId, args[1]);
                view.showAdditional(result);
            }
            default -> onUnknownCommand();
        }
    }

    private void handleStatCommand(AppType appType, long playerId) {
        String result = worker.onStatRequest(appType, playerId);
        view.showStat(result);
    }

    private void onUnknownCommand() {
        view.showAdditional(TextConstants.UNKNOWN_COMMAND_MESSAGE);
    }

}

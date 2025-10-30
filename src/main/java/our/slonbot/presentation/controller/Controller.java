package our.slonbot.presentation.controller;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;
import our.slonbot.presentation.TextConstants;
import our.slonbot.worker.IWorker;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void onCommand(long PlayerId, AppType appType, String command) {
        String[] args = command.split(" ");
        if (args.length == 0) {
            return;
        }
        switch (args[0]) {
            case "eat" -> {
                switch (args.length) {
                    case 1 -> view.showFood(Food.foodMap.values()); // worker.get_food_list
                    case 2 -> onEatingRequest(args[1]); //
                    default -> onUnknownCommand();
                }
            }
            case "work" -> {
                switch (args.length) {
                    case 1 -> view.showWork((List<Work>) Work.workMap.values());  // worker.get_work_list
                    case 2 -> worker.onWorkRequest(args[1]);
                    default -> onUnknownCommand();
                }
            }
            case "stat" -> onStatRequest();
            case "help" -> view.showHelp();
            default -> onUnknownCommand();
        }
    }



    void onEatRequest(long PlayerId, AppType appType, String foodName) {

    }

    void onWorkRequest(String workName) {

    }

    void onStatRequest() {

    }

    void onEatingRequest(String foodId) {

    }

    void onWorkRequest(Player player, String WorkId) {
        if (!Work.workMap.containsKey(WorkId)) {
            view.showAdditional(TextConstants.WRONG_WORK_NAME_MESSAGE);
            return;
        }
        Work work = Work.workMap.get(WorkId);
        view.showAdditional(TextConstants.WORK_START_MESSAGE);
        try {
            TimeUnit.SECONDS.sleep(work.time());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (!GoToWork(player, WorkId)) {
            onError();
            return;
        }
        view.showAdditional(TextConstants.WORK_END_MESSAGE);
    }

    void onStatRequest(Player player) {
        view.showStat(player);
    }

    private void onUnknownCommand() {
        view.showAdditional(TextConstants.UNKNOWN_COMMAND_MESSAGE);
    }

    private void onError() {
        view.showAdditional(TextConstants.INTERNAL_ERROR_MESSAGE);
    }
}

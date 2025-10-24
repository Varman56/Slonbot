package our.slonbot.presentation.controller;

import our.slonbot.connection.IDataWorker;
import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;
import our.slonbot.presentation.TextConstants;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Controller {

    private final IView view;
    private final IReader reader;
    private final IDataWorker worker;

    public Controller(IReader reader, IView view, IDataWorker worker) {
        this.view = view;
        this.reader = reader;
        this.worker = worker;
    }

    public void start() {
        view.showWelcome();
        AppType appType = AppType.Console; // It will handler route
        Player player = worker.getPlayer(appType, 0); // It will handler route
        while (true) {
            var command = reader.readLine();
            onCommand(player, command);

        }
    }

    public void onCommand(Player player, String command) {
        String[] args = command.split(" ");
        if (args.length == 0) {
            return;
        }
        switch (args[0]) {
            case "eat" -> {
                switch (args.length) {
                    case 1 -> view.showFood(Food.foodMap.values());
                    case 2 -> onEatingRequest(player, args[1]);
                    default -> onUnknownCommand();
                }
            }
            case "work" -> {
                switch (args.length) {
                    case 1 -> view.showWork((List<Work>) Work.workMap.values());
                    case 2 -> onWorkRequest(player, args[1]);
                    default -> onUnknownCommand();
                }
            }
            case "stat" -> onStatRequest(player);
            case "help" -> view.showHelp();
            default -> onUnknownCommand();
        }
    }

    private void onUnknownCommand() {
        view.showAdditional(TextConstants.UNKNOWN_COMMAND_MESSAGE);
    }

    private void onUnknownId() {
        view.showAdditional(TextConstants.UNKNOWN_ID_MESSAGE);
    }

    private void onError() {
        view.showAdditional(TextConstants.INTERNAL_ERROR_MESSAGE);
    }

    public boolean EatFood(Player player, String foodName) {
        Food food = Food.foodMap.get(foodName);
        if (worker.updatePlayerExp(player.id, food.exp())) {
            player.exp += food.exp();
            return true;
        }
        return false;
    }

    void onEatingRequest(Player player, String foodId) {
        if (!Food.foodMap.containsKey(foodId)) {
            view.showAdditional(TextConstants.WRONG_FOOD_NAME_MESSAGE);
            return;
        }
        Food food = Food.foodMap.get(foodId);
        if (!EatFood(player, foodId)) {
            onError();
            return;
        }
        view.showAdditional(TextConstants.EAT_SUCCESS_MESSAGE_PREFIX + food.title() + TextConstants.EAT_SUCCESS_MESSAGE_SUFFIX);
    }

    public boolean GoToWork(Player player, String WorkId) {
        Work work = Work.workMap.get(WorkId);
        if (worker.updatePlayerExpAndMoney(player.id, work.exp(), work.money())) {
            player.exp += work.exp();
            player.money += work.money();
            return true;
        }
        return false;
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
}

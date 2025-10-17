package our.slonbot.presentation.controller;

import our.slonbot.connection.IDataWorker;
import our.slonbot.model.Food;
import our.slonbot.model.Work;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;

import java.util.concurrent.TimeUnit;

public class Controller {

    private final IView view;
    private final IReader reader;
    private final IDataWorker database;

    public Controller(IReader reader, IView view, IDataWorker database) {
        this.view = view;
        this.reader = reader;
        this.database = database;
    }

    public void start() {
        view.showWelcome();

        while (true) {
            String[] args = reader.readLine().split(" ");
            if (args.length == 0) {
                continue;
            }
            switch (args[0]) {
                case "eat" -> {
                    switch (args.length) {
                        case 1 -> view.showFood(database.getAllFoods());
                        case 2 -> onEatingRequest(args[1]);
                        default -> onUnknownCommand();
                    }
                }
                case "work" -> {
                    switch (args.length) {
                        case 1 -> view.showWork(database.getAllWorks());
                        case 2 -> onWorkRequest(args[1]);
                        default -> onUnknownCommand();
                    }
                }
                case "stat" -> onStatRequest();
                case "help" -> view.showHelp();
                default -> onUnknownCommand();
            }
        }
    }

    private static boolean isPositiveInteger(String str) {
        return str != null && str.matches("\\d+");
    }

    private void onUnknownCommand() {
        view.showAdditional("Я тебя непонимат :--(");
    }

    private void onUnknownId() {
        view.showAdditional("Id неверный");
    }

    private int checkListId(String id, int type) {
        if (!isPositiveInteger(id)) {
            onUnknownCommand();
            return -1;
        }
        int digitId = Integer.parseInt(id);
        int listSize;
        switch (type) {
            case 0 -> listSize = database.getAllFoods().size();
            case 1 -> listSize = database.getAllWorks().size();
            default -> {
                return -1;
            }
        }
        if (digitId < 0 || digitId >= listSize) {
            onUnknownId();
            return -1;
        }
        return digitId;
    }

    void onEatingRequest(String foodId) {
        int id = checkListId(foodId, 0);
        if (id < 0)   return;
        Food food = database.getFoodById(id);
        view.showAdditional("Мммм " + food.title() + ". Обожаю!");

        database.updatePlayerExp(0, food.exp());
    }

    void onWorkRequest(String WorkId) {
        int id = checkListId(WorkId, 1);
        if (id < 0)  return;
        Work work = database.getWorkById(id);
        view.showAdditional("Пора вкалывать...");
        try {
            TimeUnit.SECONDS.sleep(work.time());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        view.showAdditional("А вот и ЗП капнула!");

        database.updatePlayerExp(0, work.exp());
        database.updatePlayerMoney(0, work.money());
    }

    void onStatRequest() {
        view.showStat(database.getPlayerById(0));
    }
}

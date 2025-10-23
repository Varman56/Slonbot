package our.slonbot.presentation.controller;

import our.slonbot.connection.IDataWorker;
import our.slonbot.connection.InMemoryDataWorker;
import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;
import our.slonbot.presentation.view.IView;
import our.slonbot.reader.IReader;

import java.util.List;
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
        AppType appType = AppType.Console;
        IDataWorker dataWorker = new InMemoryDataWorker();
        Player player = dataWorker.getPlayer(appType,0);
        while (true) {
            String[] args = reader.readLine().split(" ");
            if (args.length == 0) {
                continue;
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
    }

    private void onUnknownCommand() {
        view.showAdditional("Я тебя непонимат :--(");
    }
    private void onUnknownId() {
        view.showAdditional("Id неверный");
    }
    private void onError() {
        view.showAdditional("Внутренняя обшибка");
    }

    void onEatingRequest(Player player, String foodId) {
        if(!Food.foodMap.containsKey(foodId)){
            view.showAdditional("Неправильное название еды");
            return;
        }
        Food food = Food.foodMap.get(foodId);
        if (player.EatFood(foodId)) {
            view.showAdditional("Мммм " + food.title() + ". Обожаю!");
        }else {
            onError();
        }
    }

    void onWorkRequest(Player player, String WorkId) {
        if(!Work.workMap.containsKey(WorkId)){
            view.showAdditional("Неправильное название работы");
            return;
        }
        Work work = Work.workMap.get(WorkId);
        if(!player.GoToWork(WorkId)){
            onError();
            return;
        }
        view.showAdditional("Пора вкалывать...");
        try {
            TimeUnit.SECONDS.sleep(work.time());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        view.showAdditional("А вот и ЗП капнула!");
    }

    void onStatRequest(Player player) {
        view.showStat(player);
    }
}

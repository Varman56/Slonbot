package our.slonbot;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import our.slonbot.connection.HibernateDataManager;
import our.slonbot.connection.IDataManager;
import our.slonbot.presentation.controller.Controller;
import our.slonbot.presentation.view.IView;
import our.slonbot.presentation.view.TelegramView;
import our.slonbot.worker.IWorker;
import our.slonbot.worker.Worker;
import our.slonbot.connection.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        var token = "8376573183:AAE4yO_4MjIOwA1ur2NEfJJNbMrxKK4gHXE";
        TelegramClient client = new OkHttpTelegramClient(token);
        IView view = new TelegramView(client);
        IDataManager dataManager = new HibernateDataManager();
        IWorker worker = new Worker(dataManager);
        Controller controller = new Controller(view, worker);
        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(token, controller);
            System.out.println("slonbot successfully started!");
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
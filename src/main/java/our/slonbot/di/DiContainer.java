package our.slonbot.di;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import our.slonbot.connection.HibernateDataManager;
import our.slonbot.connection.IDataManager;
import our.slonbot.presentation.controller.Controller;
import our.slonbot.presentation.view.IView;
import our.slonbot.presentation.view.TelegramView;
import our.slonbot.worker.IWorker;
import our.slonbot.worker.Worker;

import java.util.HashMap;
import java.util.Map;

public class DiContainer {
    private Map<Class<?>, Object> instances = new HashMap<>() ;
    public <T> void register (Class<T> type, T instance) {
        instances.put (type, instance);
    }
    public <T> T get (Class<T> type) {
        return type.cast(instances.get(type));
    }
    public void initialize() {
        var token = "8376573183:AAE4yO_4MjIOwA1ur2NEfJJNbMrxKK4gHXE";
        TelegramClient client = new OkHttpTelegramClient(token);
        IView view = new TelegramView(client);
        IDataManager dataManager = new HibernateDataManager();
        IWorker worker = new Worker(dataManager);
        Controller controller = new Controller(view, worker);
        register(String.class, token);
        register(TelegramClient.class, client);
        register(IView.class, view);
        register(IDataManager.class, dataManager);
        register(IWorker.class, worker);
        register(Controller.class, controller);
    }
}

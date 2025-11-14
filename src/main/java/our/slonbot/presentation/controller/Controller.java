package our.slonbot.presentation.controller;

import our.slonbot.model.AppType;
import our.slonbot.presentation.view.IView;
import our.slonbot.presentation.TextConstants;
import our.slonbot.worker.IWorker;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class Controller implements LongPollingSingleThreadUpdateConsumer {

    private final IView view;
    private final IWorker worker;
    public Controller(IView view, IWorker worker) {
        this.view = view;
        this.worker = worker;
    }

    @Override
    public void consume(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            var user_id = update.getMessage().getFrom().getId();
            var message_text = update.getMessage().getText();
            var chat_id = update.getMessage().getChatId();
            view.prepare(chat_id);
            onCommand(user_id, AppType.Telegram, message_text);
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

package our.slonbot.presentation.view;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class TelegramView extends ConsoleView implements IView {
    public TelegramView(TelegramClient client) {
        this.client = client;
    }

    public void prepare(long chat_id) {
        this.chat_id = chat_id;
    }
    private final TelegramClient client;
    private long chat_id;
    @Override
    public void showAdditional(String s){
        SendMessage message = SendMessage // Create a message object
                .builder()
                .chatId(chat_id)
                .text(s)
                .build();
        try {
            client.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

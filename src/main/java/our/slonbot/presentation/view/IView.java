package our.slonbot.presentation.view;

public interface IView {
    void prepare(long chat_id);
    void showWelcome();
    void showHelp();
    void showStat(String statMessage);
    void showFood(String foodInfo);
    void showWork(String workInfo);
    void showAdditional(String s);
}

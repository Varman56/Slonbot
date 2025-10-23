package our.slonbot.presentation.view;

import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.List;

public interface IView {
    void showWelcome();
    void showHelp();
    void showStat(Player player);
    void showFood(Iterable<Food> foodList);
    void showWork(Iterable<Work> workList);
    void showAdditional(String s);
    // TODO: Slonyara functionality
}

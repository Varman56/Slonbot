package our.slonbot.presentation.view;

import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.List;

public class ConsoleView implements IView {
    public void showWelcome() {
        System.out.println("");
    }
    public void showHelp() {
        System.out.println("");
    }
    public void showStat(Player player) {
        System.out.println("");
    }
    public void showFood(List<Food> foodList) {
        System.out.println("");
    }
    public void showWork(List<Work> workList) {
        System.out.println("");
    }
    public void showAdditional(String s){
        System.out.println(s);
    }
}

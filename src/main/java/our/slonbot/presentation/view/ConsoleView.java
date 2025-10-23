package our.slonbot.presentation.view;

import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;
import our.slonbot.presentation.TextConstants;

import java.util.List;

public class ConsoleView implements IView {
    public void showWelcome() {
        System.out.println(TextConstants.WELCOME_MESSAGE);
    }
    public void showHelp() {
        System.out.println(TextConstants.HELP_MESSAGE);
    }
    public void showStat(Player player) {
        String formattedString = String.format("""
                %s
                %s
                %s
                %s
                """,
                String.format(TextConstants.PLAYER_STAT_NAME, player.name),
                String.format(TextConstants.PLAYER_STAT_AGE, player.level),
                String.format(TextConstants.PLAYER_STAT_EXP, player.exp),
                String.format(TextConstants.PLAYER_STAT_MONEY, player.money));
        System.out.println(formattedString);
    }
    public void showFood(Iterable<Food> foodList) {
        System.out.println(TextConstants.AVAILABLE_FOOD_HEADER);
        System.out.println(TextConstants.SEPARATOR);
        for (Food food : foodList) {
            String formattedString = String.format("""
                %s - %d
                
                %s
                %s
                %d
                %s
                """, food.title(), food.id(), food.description(), TextConstants.FOOD_DESCRIPTION_EXP, food.exp(), TextConstants.SEPARATOR);
            System.out.println(formattedString);
        }
    }
    public void showWork(Iterable<Work> workList) {
        System.out.println(TextConstants.AVAILABLE_WORK_HEADER);
        System.out.println(TextConstants.SEPARATOR);
        for (Work work : workList) {
            String formattedString = String.format("""
                %s - %d
                
                %s
                %s
                %d
                %s
                %d
                %s
                """, work.title(), work.id(), work.description(), TextConstants.WORK_DESCRIPTION_EXP, work.exp(), TextConstants.WORK_DESCRIPTION_MONEY, work.money(), TextConstants.SEPARATOR);
            System.out.println(formattedString);
        }
    }
    public void showAdditional(String s){
        System.out.println(s);
    }
}
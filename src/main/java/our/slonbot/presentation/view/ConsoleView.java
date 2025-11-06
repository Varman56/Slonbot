package our.slonbot.presentation.view;

import our.slonbot.presentation.TextConstants;



public class ConsoleView implements IView {
    public void showWelcome() {
        System.out.println(TextConstants.WELCOME_MESSAGE);
    }
    public void showHelp() {
        System.out.println(TextConstants.HELP_MESSAGE);
    }
    public void showStat(String statMessage) {
        System.out.println(statMessage);
    }
    public void showFood(String foodInfo) {
        System.out.println(foodInfo);
    }
    public void showWork(String workInfo) {
        System.out.println(workInfo);
    }
    public void showAdditional(String s){
        System.out.println(s);
    }
}
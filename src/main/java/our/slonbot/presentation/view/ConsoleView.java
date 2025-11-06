package our.slonbot.presentation.view;

import our.slonbot.presentation.TextConstants;



public class ConsoleView implements IView {
    public void prepare(long a){}
    public void showWelcome() {
        showAdditional(TextConstants.WELCOME_MESSAGE);
    }
    public void showHelp() {
        showAdditional(TextConstants.HELP_MESSAGE);
    }
    public void showStat(String statMessage) {
        showAdditional(statMessage);
    }
    public void showFood(String foodInfo) {
        showAdditional(foodInfo);
    }
    public void showWork(String workInfo) {
        showAdditional(workInfo);
    }
    @Override
    public void showAdditional(String s){
        System.out.println(s);
    }
}
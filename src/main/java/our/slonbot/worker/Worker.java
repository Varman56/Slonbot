package our.slonbot.worker;

import our.slonbot.connection.IDataManager;
import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;
import our.slonbot.presentation.TextConstants;

import java.util.List;

public class Worker implements IWorker {
    private final IDataManager dataManager;

    public Worker(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public String onEatingRequest(AppType appType, long playerIdValue, String foodName) {
        Food food = dataManager.getFood(foodName);

        if (food == null) {
            return TextConstants.WRONG_FOOD_NAME_MESSAGE;
        }

        if (dataManager.updatePlayerExp(appType, playerIdValue, food.getExp())) {
            return TextConstants.EAT_SUCCESS_MESSAGE_PREFIX + food.getName() + TextConstants.EAT_SUCCESS_MESSAGE_SUFFIX;
        }

        return TextConstants.INTERNAL_ERROR_MESSAGE;
    }

    @Override
    public String onWorkRequest(AppType appType, long playerIdValue, String workName) {
        Work work = dataManager.getWork(workName);

        if (work == null) {
            return TextConstants.WRONG_WORK_NAME_MESSAGE;
        }

        if (dataManager.updatePlayerExpAndMoney(appType, playerIdValue, work.getExp(), work.getMoney())) {
            return TextConstants.WORK_SUCCESS_MESSAGE_PREFIX + work.getName() + TextConstants.WORK_SUCCESS_MESSAGE_SUFFIX;
        }
        return TextConstants.INTERNAL_ERROR_MESSAGE;

    }

    @Override
    public String onStatRequest(AppType appType, long playerIdValue) {
        Player player = dataManager.getPlayer(appType, playerIdValue);
        return String.format("Имя: %s, Опыт: %d, Уровень: %d, Деньги: %d",
                player.getName(), player.getExp(), player.getLevel(), player.getMoney());
    }

    @Override
    public String getAllFoodInfo() {
        List<Food> foodList = dataManager.getAllFood();

        StringBuilder sb = new StringBuilder();
        sb.append(TextConstants.AVAILABLE_FOOD_HEADER).append("\n");
        sb.append(TextConstants.SEPARATOR).append("\n");
        for (Food food : foodList) {
            sb.append(String.format(
                    "%s - %s\n  %s %d\n%s\n",
                    food.getName(), food.getDescription(),
                    TextConstants.FOOD_DESCRIPTION_EXP, food.getExp(),
                    TextConstants.SEPARATOR));
        }
        return sb.toString();
    }

    @Override
    public String getAllWorkInfo() {
        List<Work> workList = dataManager.getAllWork();

        StringBuilder sb = new StringBuilder();
        sb.append(TextConstants.AVAILABLE_WORK_HEADER).append("\n");
        sb.append(TextConstants.SEPARATOR).append("\n");
        for (Work work : workList) {
            sb.append(String.format(
                    "%s - %s\n  %s %d\n  %s %d\n  Время: %d\n%s\n",
                    work.getName(), work.getDescription(),
                    TextConstants.WORK_DESCRIPTION_EXP, work.getExp(),
                    TextConstants.WORK_DESCRIPTION_MONEY, work.getMoney(),
                    work.getTime(),
                    TextConstants.SEPARATOR));
        }
        return sb.toString();
    }
}

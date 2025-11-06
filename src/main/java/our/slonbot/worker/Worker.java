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
        } else {
            return TextConstants.INTERNAL_ERROR_MESSAGE;
        }
    }

    @Override
    public String onWorkRequest(AppType appType, long playerIdValue, String workName) {
        Work work = dataManager.getWork(workName);

        if (work == null) {
            return TextConstants.WRONG_WORK_NAME_MESSAGE;
        }

        if (dataManager.updatePlayerExpAndMoney(appType, playerIdValue, work.getExp(), work.getMoney())) {
            return TextConstants.WORK_SUCCESS_MESSAGE_PREFIX + work.getName() + TextConstants.WORK_SUCCESS_MESSAGE_SUFFIX;
        } else {
            return TextConstants.INTERNAL_ERROR_MESSAGE;
        }
    }

    @Override
    public String onStatRequest(AppType appType, long playerIdValue) {
        Player player = dataManager.getPlayer(appType, playerIdValue);
        if (player != null) {
            return String.format("Имя: %s\nОпыт: %d\nУровень: %d\nДеньги: %d",
                    player.getName(), player.getExp(), player.getLevel(), player.getMoney());
        } else {
            return TextConstants.INTERNAL_ERROR_MESSAGE;
        }
    }

    @Override
    public Food getFood(String name) {
        return dataManager.getFood(name);
    }

    @Override
    public Work getWork(String name) {
        return dataManager.getWork(name);
    }

    @Override
    public List<Food> getAllFood() {
        return dataManager.getAllFood();
    }

    @Override
    public List<Work> getAllWork() {
        return dataManager.getAllWork();
    }
}

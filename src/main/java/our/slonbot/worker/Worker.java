package our.slonbot.worker;

import our.slonbot.connection.IDataManager;
import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;
import our.slonbot.presentation.TextConstants;

public class Worker implements IWorker {
    private final IDataManager dataWorker;

    public Worker(IDataManager dataWorker) {
        this.dataWorker = dataWorker;
    }

    public String EatFood(long PlayerId, AppType appType, String foodName) {
        Player player = dataWorker.getPlayer(appType, PlayerId);
        if (!dataWorker.) {
            view.showAdditional(TextConstants.WRONG_FOOD_NAME_MESSAGE);
            return TextConstants.WRONG_FOOD_NAME_MESSAGE;
        }
        Food food = Food.foodMap.get(foodId);
        if (!EatFood(player, foodId)) {
            onError();
            return;
        }
        view.showAdditional(TextConstants.EAT_SUCCESS_MESSAGE_PREFIX + food.title() + TextConstants.EAT_SUCCESS_MESSAGE_SUFFIX);
        Food food = Food.foodMap.get(foodName);
        if (dataWorker.updatePlayerExp(player.id, food.exp())) {
            player.exp += food.exp();
            return true;
        }
        return false;
    }

    public boolean GoToWork(Player player, String WorkId) {
        Work work = Work.workMap.get(WorkId);
        if (worker.updatePlayerExpAndMoney(player.id, work.exp(), work.money())) {
            player.exp += work.exp();
            player.money += work.money();
            return true;
        }
        return false;
    }


    @Override
    public boolean onEatingRequest(String foodName) {
        return false;
    }

    @Override
    public boolean onWorkRequest(String workName) {
        return false;
    }

    @Override
    public boolean onStatRequest() {
        return false;
    }
}

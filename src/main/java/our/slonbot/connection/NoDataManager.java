package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.PlayerByAppId;
import our.slonbot.model.Work;

import java.util.Collections;
import java.util.List;

public class NoDataManager implements IDataManager {

    @Override
    public Player getPlayer(AppType appType, long playerIdValue) {
        PlayerByAppId playerKey = new PlayerByAppId(playerIdValue, appType);
        // Возвращаем нового игрока с фиктивным PlayerByAppId, так как данные не хранятся
        return new Player(playerKey, "NoDataPlayer", 0, 1, 0);
    }

    @Override
    public Food getFood(String name) {
        return null;
    }

    @Override
    public Work getWork(String name) {
        return null;
    }

    @Override
    public List<Food> getAllFood() {
        return Collections.emptyList();
    }

    @Override
    public List<Work> getAllWork() {
        return Collections.emptyList();
    }

    @Override
    public boolean updatePlayerExp(AppType appType, long playerIdValue, long deltaExp) {
        return true;
    }

    @Override
    public boolean updatePlayerMoney(AppType appType, long playerIdValue, int deltaMoney) {
        return true;
    }

    @Override
    public boolean updatePlayerExpAndMoney(AppType appType, long playerIdValue, long deltaExp, int deltaMoney) {
        return true;
    }
}
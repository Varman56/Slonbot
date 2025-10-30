package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.List;

public interface IDataManager {
    Player getPlayer(AppType appType, long appId);
    Food getFood(String name);
    Work getWork(String name);
    List<Food> getAllFood();
    List<Work> getAllWork();
    boolean updatePlayerExp(AppType appType, long id, long deltaExp);
    boolean updatePlayerMoney(AppType appType, long id, int deltaMoney);
    boolean updatePlayerExpAndMoney(AppType appType, long id, long deltaExp, int deltaMoney);
}

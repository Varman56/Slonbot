package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.List;

public interface IDataWorker {
    Player getPlayer(AppType appType, int appId);
    boolean updatePlayerExp(long id, long deltaExp);

    boolean updatePlayerMoney(long id, int deltaMoney);

    boolean updatePlayerExpMoney(long id, long deltaExp, int deltaMoney);
}

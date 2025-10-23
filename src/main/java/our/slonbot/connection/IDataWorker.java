package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Player;

public interface IDataWorker {
    Player getPlayer(AppType appType, int appId);
    boolean updatePlayerExp(long id, long deltaExp);

    boolean updatePlayerMoney(long id, int deltaMoney);

    boolean updatePlayerExpAndMoney(long id, long deltaExp, int deltaMoney);
}

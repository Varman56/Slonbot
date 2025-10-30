package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Player;

public class NoDataManager implements IDataManager {

    public Player getPlayer(AppType appType, int appId) {
        return new Player();
    }

    public boolean updatePlayerExp(long id, long deltaExp) {
        return true;
    }

    public boolean updatePlayerMoney(long id, int deltaMoney) {
        return true;
    }

    public boolean updatePlayerExpAndMoney(long id, long deltaExp, int deltaMoney) {
        return true;
    }

}
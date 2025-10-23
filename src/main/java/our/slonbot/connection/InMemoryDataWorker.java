package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataWorker implements IDataWorker {
    private static final long CONSOLE_PLAYER_ID = 0L;

    private Player currentPlayer;

    public InMemoryDataWorker() {}

    public Player getPlayer(AppType appType, int appId) {
        currentPlayer = new Player();
        currentPlayer.id = CONSOLE_PLAYER_ID;
        currentPlayer.name = "ConsolePlayer";
        currentPlayer.level = 1;
        return currentPlayer;
    }

    public Player getPlayerById(long id) {
        return currentPlayer;
    }

    public boolean updatePlayerExp(long id, long deltaExp) {
        currentPlayer.exp += deltaExp;
        return true;
    }

    public boolean updatePlayerMoney(long id, int deltaMoney) {
        currentPlayer.money += deltaMoney;
        return true;
    }
    public boolean updatePlayerExpAndMoney(long id, long deltaExp, int deltaMoney) {
        currentPlayer.money += deltaMoney;
        currentPlayer.exp += deltaExp;
        return true;
    }


}

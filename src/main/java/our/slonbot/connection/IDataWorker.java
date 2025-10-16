package our.slonbot.connection;

import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.List;

public interface IDataWorker {
    Player getPlayerById(long id);

    Work getWorkById(int id);

    Food getFoodById(int id);

    void setWorkActivity(long playerId, int workId);

    void updatePlayerExp(long id, int deltaExp);

    List<Food> getAllFoods();

    List<Work> getAllWorks();
}

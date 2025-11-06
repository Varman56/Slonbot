package our.slonbot.worker;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Work;

import java.util.List;

public interface IWorker {
    String onEatingRequest(AppType appType, long playerId, String foodName);
    String onWorkRequest(AppType appType, long playerId, String workName);
    String onStatRequest(AppType appType, long playerId);
    Food getFood(String name);
    Work getWork(String name);
    List<Food> getAllFood();
    List<Work> getAllWork();
}

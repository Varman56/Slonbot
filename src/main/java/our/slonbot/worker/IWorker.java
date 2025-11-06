package our.slonbot.worker;

import our.slonbot.model.AppType;

public interface IWorker {
    String onEatingRequest(AppType appType, long playerId, String foodName);
    String onWorkRequest(AppType appType, long playerId, String workName);
    String onStatRequest(AppType appType, long playerId);
    String getAllFoodInfo();
    String getAllWorkInfo();
}

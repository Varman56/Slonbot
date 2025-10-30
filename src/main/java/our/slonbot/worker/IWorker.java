package our.slonbot.worker;

public interface IWorker {
    boolean onEatingRequest(String foodName);
    boolean onWorkRequest(String workName);
    boolean onStatRequest();
}

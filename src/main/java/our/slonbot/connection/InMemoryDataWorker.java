package our.slonbot.connection;

import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDataWorker implements IDataWorker {
    private static final long CONSOLE_PLAYER_ID = 0L;

    private Player currentPlayer;
    private final Map<Integer, Work> works = new HashMap<>();
    private final Map<Integer, Food> foods = new HashMap<>();

    public InMemoryDataWorker() {
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        currentPlayer = new Player();
        currentPlayer.id = CONSOLE_PLAYER_ID;
        currentPlayer.name = "ConsolePlayer";
        currentPlayer.level = 1;

        Work work1 = new Work(0, "Поливать цветы", "", 5, 10, 3);
        Work work2 = new Work(1, "Работать на заводе", "", 15, 30, 10);

        works.put(0, work1);
        works.put(1, work2);

        Food food1 = new Food(0, "Трава", "", 1);
        Food food2 = new Food(1, "Хлеб", "", 2);

        foods.put(0, food1);
        foods.put(1, food2);
    }

    public Player getPlayerById(long id) {
        validatePlayerId(id);
        return currentPlayer;
    }

    public Work getWorkById(int id) {
        Work work = works.get(id);
        validateWorkId(id);
        return work;
    }

    public Food getFoodById(int id) {
        Food food = foods.get(id);
        validateFoodId(id);
        return food;
    }

    public void setWorkActivity(long playerId, int workId) {
        validatePlayerId(playerId);
        validateWorkId(workId);
    }

    public void updatePlayerExp(long id, long deltaExp) {
        validatePlayerId(id);
        currentPlayer.exp += deltaExp;
    }

    public void updatePlayerMoney(long id, int deltaMoney) {
        validatePlayerId(id);
        currentPlayer.money += deltaMoney;
    }

    public List<Work> getAllWorks() {
        return new ArrayList<>(works.values());
    }

    public List<Food> getAllFoods() {
        return new ArrayList<>(foods.values());
    }

    private void validatePlayerId(long playerId) {
        if (playerId != CONSOLE_PLAYER_ID) {
            throw new IllegalArgumentException("Неверный id игрока: " + playerId);
        }
    }

    private void validateWorkId(int workId) {
        Work work = works.get(workId);
        if (work == null) {
            throw new IllegalArgumentException("Неверный id работы");
        }
    }

    private void validateFoodId(int FoodId) {
        Food food = foods.get(FoodId);
        if (food == null) {
            throw new IllegalArgumentException("Неверный id еды");
        }
    }


}

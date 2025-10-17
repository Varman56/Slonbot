package our.slonbot.connection;

import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataWorker implements IDataWorker {
    private static final long CONSOLE_PLAYER_ID = 0L;

    private Player currentPlayer;
    private final List<Work> works = new ArrayList<>();
    private final List<Food> foods = new ArrayList<>();

    public InMemoryDataWorker() {
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        currentPlayer = new Player();
        currentPlayer.id = CONSOLE_PLAYER_ID;
        currentPlayer.name = "ConsolePlayer";
        currentPlayer.level = 1;

        Work work0 = new Work(0, "Поливать цветы", "Зачем-то же хобот растёт, правда?", 5, 10, 3);
        Work work1 = new Work(1, "Работать на заводе", "Мой дед работал на заводе, мой батя работал на заводе", 15, 30, 10);

        works.add(work0);
        works.add(work1);

        Food food0 = new Food(0, "Трава", "Зелененькая африканская (легальная)", 1);
        Food food1 = new Food(1, "Хлеб", "Отобранный у туристов особенно вкусный", 2);

        foods.add(food0);
        foods.add(food1);
    }

    public Player getPlayerById(long id) {
        validatePlayerId(id);
        return currentPlayer;
    }

    public Work getWorkById(int id) {
        validateWorkId(id);
        return works.get(id);
    }

    public Food getFoodById(int id) {
        validateFoodId(id);
        return foods.get(id);
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
        return works;
    }

    public List<Food> getAllFoods() {
        return foods;
    }

    private void validatePlayerId(long playerId) {
        if (playerId != CONSOLE_PLAYER_ID) {
            throw new IllegalArgumentException("Неверный id игрока: " + playerId);
        }
    }

    private void validateWorkId(int workId) {
        if (workId < 0 || workId >= works.size()) {
            throw new IllegalArgumentException("Неверный id работы");
        }
    }

    private void validateFoodId(int FoodId) {
        if (FoodId < 0 || FoodId >= foods.size()) {
            throw new IllegalArgumentException("Неверный id еды");
        }
    }


}

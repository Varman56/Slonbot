package our.slonbot.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDataWorkerTest {

    private InMemoryDataWorker dataWorker;

    @BeforeEach
    void setUp() {
        dataWorker = new InMemoryDataWorker();
    }

    @Test
    void getPlayerById_validId_returnsCurrentPlayer() {
        Player player = dataWorker.getPlayerById(0L);
        assertNotNull(player);
        assertEquals(0L, player.id);
        assertEquals("ConsolePlayer", player.name);
        assertEquals(1, player.level);
    }

    @Test
    void getPlayerById_invalidId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> dataWorker.getPlayerById(1L));
    }

    @Test
    void getWorkById_validId_returnsWork() {
        Work work = dataWorker.getWorkById(0);
        assertNotNull(work);
        assertEquals(0, work.id());
        assertEquals("Работать на заводе", work.title());
    }

    @Test
    void getWorkById_invalidId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> dataWorker.getWorkById(-1));
        assertThrows(IllegalArgumentException.class, () -> dataWorker.getWorkById(100));
    }

    @Test
    void getFoodById_validId_returnsFood() {
        Food food = dataWorker.getFoodById(0);
        assertNotNull(food);
        assertEquals(0, food.id());
        assertEquals("Хлеб", food.title());
    }

    @Test
    void getFoodById_invalidId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> dataWorker.getFoodById(-1));
        assertThrows(IllegalArgumentException.class, () -> dataWorker.getFoodById(100));
    }

    @Test
    void setWorkActivity_validIds_noException() {
        assertDoesNotThrow(() -> dataWorker.setWorkActivity(0L, 0));
    }

    @Test
    void setWorkActivity_invalidPlayerId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> dataWorker.setWorkActivity(1L, 0));
    }

    @Test
    void setWorkActivity_invalidWorkId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> dataWorker.setWorkActivity(0L, -1));
    }

    @Test
    void updatePlayerExp_validId_updatesExperience() {
        Player player = dataWorker.getPlayerById(0L);
        long initialExp = player.exp;
        dataWorker.updatePlayerExp(0L, 50);
        assertEquals(initialExp + 50, player.exp);
    }

    @Test
    void updatePlayerExp_invalidId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> dataWorker.updatePlayerExp(1L, 50));
    }

    @Test
    void updatePlayerMoney_validId_updatesMoney() {
        Player player = dataWorker.getPlayerById(0L);
        int initialMoney = player.money;
        dataWorker.updatePlayerMoney(0L, 100);
        assertEquals(initialMoney + 100, player.money);
    }

    @Test
    void updatePlayerMoney_invalidId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> dataWorker.updatePlayerMoney(1L, 100));
    }

    @Test
    void getAllWorks_returnsAllWorks() {
        List<Work> works = dataWorker.getAllWorks();
        assertNotNull(works);
        assertEquals(2, works.size());
        assertEquals("Поливать цветы", works.get(0).title());
        assertEquals("Работать на заводе", works.get(1).title());
    }

    @Test
    void getAllFoods_returnsAllFoods() {
        List<Food> foods = dataWorker.getAllFoods();
        assertNotNull(foods);
        assertEquals(2, foods.size());
        assertEquals("Трава", foods.get(0).title());
        assertEquals("Хлеб", foods.get(1).title());
    }
}

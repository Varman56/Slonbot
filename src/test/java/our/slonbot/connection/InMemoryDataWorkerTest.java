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
}

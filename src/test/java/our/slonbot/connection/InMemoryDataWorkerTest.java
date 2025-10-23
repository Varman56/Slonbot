package our.slonbot.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import our.slonbot.model.AppType;
import our.slonbot.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDataWorkerTest {

    private InMemoryDataWorker dataWorker;

    @BeforeEach
    void setUp() {
        dataWorker = new InMemoryDataWorker();
    }

    @Test
    void getPlayer_returnsNewPlayer() {
        Player player = dataWorker.getPlayer(AppType.Console, 0);
        assertNotNull(player);
        assertEquals(0L, player.id);
        assertEquals("ConsolePlayer", player.name);
        assertEquals(1, player.level);
        assertEquals(0L, player.exp);
        assertEquals(0, player.money);
    }

    @Test
    void getPlayerById_validId_returnsCurrentPlayer() {
        // Need to call getPlayer first to initialize currentPlayer
        dataWorker.getPlayer(AppType.Console, 0);
        Player player = dataWorker.getPlayerById(0L);
        assertNotNull(player);
        assertEquals(0L, player.id);
        assertEquals("ConsolePlayer", player.name);
    }

    @Test
    void updatePlayerExp_validId_updatesExperience() {
        dataWorker.getPlayer(AppType.Console, 0);
        long initialExp = dataWorker.getPlayerById(0L).exp;
        assertTrue(dataWorker.updatePlayerExp(0L, 50));
        assertEquals(initialExp + 50, dataWorker.getPlayerById(0L).exp);
    }


    @Test
    void updatePlayerMoney_validId_updatesMoney() {
        dataWorker.getPlayer(AppType.Console, 0);
        int initialMoney = dataWorker.getPlayerById(0L).money;
        assertTrue(dataWorker.updatePlayerMoney(0L, 100));
        assertEquals(initialMoney + 100, dataWorker.getPlayerById(0L).money);
    }

    @Test
    void updatePlayerExpAndMoney_validId_updatesExpAndMoney() {
        dataWorker.getPlayer(AppType.Console, 0);
        long initialExp = dataWorker.getPlayerById(0L).exp;
        int initialMoney = dataWorker.getPlayerById(0L).money;
        assertTrue(dataWorker.updatePlayerExpAndMoney(0L, 75, 150));
        assertEquals(initialExp + 75, dataWorker.getPlayerById(0L).exp);
        assertEquals(initialMoney + 150, dataWorker.getPlayerById(0L).money);
    }
}

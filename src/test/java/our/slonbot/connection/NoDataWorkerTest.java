package our.slonbot.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import our.slonbot.model.AppType;
import our.slonbot.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class NoDataWorkerTest {

    private NoDataWorker dataWorker;

    @BeforeEach
    void setUp() {
        dataWorker = new NoDataWorker();
    }

    @Test
    void getPlayer_returnsNewPlayer() {
        Player player = dataWorker.getPlayer(AppType.Console, 0);
        assertNotNull(player);
        // Since NoDataWorker returns a new Player, we can only assert it's not null.
        // More specific assertions depend on Player's default constructor.
    }

    @Test
    void updatePlayerExp_returnsTrue() {
        assertTrue(dataWorker.updatePlayerExp(0L, 100));
    }

    @Test
    void updatePlayerMoney_returnsTrue() {
        assertTrue(dataWorker.updatePlayerMoney(0L, 50));
    }

    @Test
    void updatePlayerExpAndMoney_returnsTrue() {
        assertTrue(dataWorker.updatePlayerExpAndMoney(0L, 100, 50));
    }
}

package our.slonbot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(1L, AppType.Console, 1, "TestPlayer", 100L, 1, 50);
    }

    @Test
    void playerFullConstructor_setsAllFieldsCorrectly() {
        assertEquals(1L, player.id);
        assertEquals(AppType.Console, player.appType);
        assertEquals(1, player.appId);
        assertEquals("TestPlayer", player.name);
        assertEquals(100L, player.exp);
        assertEquals(1, player.level);
        assertEquals(50, player.money);
    }

    @Test
    void playerDefaultConstructor_createsEmptyPlayer() {
        Player defaultPlayer = new Player();
        assertNotNull(defaultPlayer);
    }

}

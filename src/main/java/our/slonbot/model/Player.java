package our.slonbot.model;

import javax.swing.text.Position;

public record Player(
        long id,
        String name,
        long exp,
        int level,
        int money) {
}

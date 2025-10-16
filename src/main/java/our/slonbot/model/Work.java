package our.slonbot.model;

public record Work(
        int id,
        String title,
        String description,
        long exp,
        long time,
        int money) {
}

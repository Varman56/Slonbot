package our.slonbot.model;

public class Work {
    int id;
    String name;
    String description;
    long exp;
    long time;
    int money;

    Work(String title, String description, int exp, int money) {
        this.name = title;
        this.description = description;
        this.exp = exp;
        this.money = money;
    }
}
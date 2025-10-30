package our.slonbot.model;

public class Player {
    public long id;
    public AppType appType;
    public long appId;
    public String name;
    public long exp;
    public int level;
    public int money;

    public Player(long id, AppType appType, long appId, String name, long exp, int level, int money) {
        this.id = id;
        this.appType = appType;
        this.appId = appId;
        this.name = name;
        this.exp = exp;
        this.level = level;
        this.money = money;
    }

    public Player() {
    }

}

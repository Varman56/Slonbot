package our.slonbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @EmbeddedId
    private PlayerByAppId id;

    @Column(name = "username", nullable = false)
    private String name;

    @Column(name = "exp")
    private long exp;

    @Column(name = "level")
    private int level;

    @Column(name = "money")
    private int money;

    public Player() {
    }

    public Player(PlayerByAppId id, String name, long exp, int level, int money) {
        this.id = id;
        this.name = name;
        this.exp = exp;
        this.level = level;
        this.money = money;
    }

    public PlayerByAppId getId() {
        return id;
    }

    public void setId(PlayerByAppId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

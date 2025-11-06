package our.slonbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "works")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private long exp;
    private long time;
    private int money;

    public Work() {
    }

    public Work(String name, String description, long exp, long time, int money) {
        this.name = name;
        this.description = description;
        this.exp = exp;
        this.time = time;
        this.money = money;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getExp() {
        return exp;
    }


    public long getTime() {
        return time;
    }

    public int getMoney() {
        return money;
    }
}
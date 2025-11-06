package our.slonbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int exp;

    public Food() {
    }

    public Food (String name, String description, int exp){
        this.name = name;
        this.description = description;
        this.exp = exp;
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

    public int getExp() {
        return exp;
    }

}

package our.slonbot.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PlayerByAppId implements Serializable {
    @Column(name = "player_id")
    private Long playerId;
    @Column(name = "app_type")
    private AppType appId;

    public PlayerByAppId() {}

    public PlayerByAppId(Long playerId, AppType appId) {
        this.playerId = playerId;
        this.appId = appId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public AppType getAppId() {
        return appId;
    }

    public void setAppId(AppType appId) {
        this.appId = appId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerByAppId that = (PlayerByAppId) o;
        return Objects.equals(playerId, that.playerId) &&
                Objects.equals(appId, that.appId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, appId);
    }
}
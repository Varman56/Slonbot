package our.slonbot.connection;

public class SQLQueries {
    public static final String CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS players (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            app_type TEXT NOT NULL,
            app_id INTEGER NOT NULL,
            name TEXT NOT NULL,
            exp BIGINT NOT NULL,
            level INTEGER NOT NULL,
            money INTEGER NOT NULL 
        )
        """;

    public static final String INSERT_PLAYER =
            "INSERT INTO players(app_type, app_id, name, exp, level, money) VALUES(?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL =
            "SELECT * FROM players";

    public static final String SELECT_BY_ID =
            "SELECT * FROM players WHERE id = ?";

    public static final String SELECT_BY_APP_TYPE_AND_APP_ID =
            "SELECT * FROM players WHERE app_type = ? AND app_id = ?";

    public static final String UPDATE_PLAYER_EXP =
            "UPDATE players SET exp = exp + ? WHERE id = ?";

    public static final String UPDATE_PLAYER_MONEY =
            "UPDATE players SET money = money + ? WHERE id = ?";

    public static final String UPDATE_PLAYER =
            "UPDATE players SET exp = exp + ?, money = money + ? WHERE id = ?";
}
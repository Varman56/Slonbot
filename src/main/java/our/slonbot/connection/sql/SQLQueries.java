package our.slonbot.connection.sql;

public class SQLQueries {
    public static final String CREATE_TABLES = """
            CREATE TABLE IF NOT EXISTS players (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                app_type TEXT NOT NULL,
                app_id INTEGER NOT NULL,
                name TEXT NOT NULL,
                exp BIGINT NOT NULL,
                level INTEGER NOT NULL,
                money INTEGER NOT NULL 
            )
            CREATE TABLE IF NOT EXISTS foods (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                description TEXT NOT NULL
                exp BIGINT NOT NULL,
            )
            CREATE TABLE IF NOT EXISTS works (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                description TEXT NOT NULL,
                exp BIGINT NOT NULL,
                work_time INT NOT NULL,
                money INT NOT NULL
            )
            """;

    public static final String INIT_TABLE_DATA = """
            INSERT INTO foods (name, description, exp) VALUES (
            ("Трава", "Зелененькая африканская (легальная)", 1),
            ("Хлеб", "Отобранный у туристов особенно вкусный", 2)
            )
            
            INSERT INTO works (name, description, exp, money) VALUES (
            ("Поливать цветы", "Зачем-то же хобот растёт, правда?", 5, 10, 3),
            ("Работать на заводе", "Мой дед работал на заводе, мой батя работал на заводе", 15, 30, 10)
            )
            """;

    public static final String COUNT_FOOD = "SELECT COUNT(*) FROM food";

    public static final String INSERT_PLAYER =
            "INSERT INTO players(app_type, app_id, name, exp, level, money) VALUES(?, ?, ?, ?, ?, ?)";

    public static final String SELECT_FOOD_ALL =
            "SELECT (name, description, exp) FROM food";

    public static final String SELECT_FOOD_BY_NAME =
            "SELECT (name, description, exp) FROM players WHERE name = ?";

    public static final String SELECT_BY_APP_TYPE_AND_APP_ID =
            "SELECT * FROM players WHERE app_type = ? AND app_id = ?";

    public static final String UPDATE_PLAYER_EXP =
            "UPDATE players SET exp = exp + ? WHERE app_type = ? AND app_id = ?";

    public static final String UPDATE_PLAYER_MONEY =
            "UPDATE players SET money = money + ? WHERE app_type = ? AND app_id = ?";

    public static final String UPDATE_PLAYER =
            "UPDATE players SET exp = exp + ?, money = money + ? WHERE app_type = ? AND app_id = ?";

    // JPQL Queries for HibernateDataManager
    public static final String JPQL_SELECT_FOOD_BY_NAME = "FROM Food WHERE name = :name";
    public static final String JPQL_SELECT_WORK_BY_NAME = "FROM Work WHERE name = :name";
    public static final String JPQL_SELECT_ALL_FOOD = "FROM Food";
    public static final String JPQL_SELECT_ALL_WORK = "FROM Work";
}
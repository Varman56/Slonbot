package our.slonbot.connection;

public class SQLQueries {
    // JPQL Queries for HibernateDataManager
    public static final String JPQL_SELECT_FOOD_BY_NAME = "FROM Food WHERE name = :name";
    public static final String JPQL_SELECT_WORK_BY_NAME = "FROM Work WHERE name = :name";
    public static final String JPQL_SELECT_ALL_FOOD = "FROM Food";
    public static final String JPQL_SELECT_ALL_WORK = "FROM Work";

    public static final String JPQL_COUNT_FOOD = "SELECT COUNT(*) FROM Food";
    public static final String JPQL_COUNT_WORK = "SELECT COUNT(*) FROM Work";
}
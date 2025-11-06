package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Food;
import our.slonbot.model.Player;
import our.slonbot.model.Work;

import java.sql.*;
import java.sql.ResultSet;
import java.util.List;

public class SQLiteManager implements IDataManager {

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/slonbot.db";
    private final Connection connection;

    public SQLiteManager() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL);
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.CREATE_TABLES)) {
            statement.execute();
        } catch (SQLException e) {
            // log error
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQLQueries.COUNT_FOOD);
            if (rs.next() && rs.getInt(1) == 0) {
                statement.executeQuery(SQLQueries.INIT_TABLE_DATA);
            }
        } catch (SQLException e) {
            // log error
        }

    }

    @Override
    public Player getPlayer(AppType appType, long appId) {
        String selectSql = SQLQueries.SELECT_BY_APP_TYPE_AND_APP_ID;
        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setString(1, appType.name());
            statement.setLong(2, appId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return mapResultSetToPlayer(resultSet);
            return createPlayer(appType, appId, connection);
        } catch (SQLException e) {
            // log
            return new Player();
        }
    }

    private Player mapResultSetToPlayer(ResultSet resultSet) throws SQLException {
        return new Player(
                resultSet.getLong("id"),
                resultSet.getLong("app_type"),
                resultSet.getInt("app_id"),
                resultSet.getString("name"),
                resultSet.getLong("exp"),
                resultSet.getInt("level"),
                resultSet.getInt("money")
        );
    }

    private Player createPlayer(AppType appType, long appId, Connection connection) throws SQLException {
        String insertSql = SQLQueries.INSERT_PLAYER;
        try (PreparedStatement statement = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            Player newPlayer = new Player(
                    -1, // ID will be set after insertion
                    appType,
                    appId,
                    "NewPlayer",
                    0,
                    1,
                    0
            );
            statement.setInt(1, newPlayer.appType.ordinal());
            statement.setLong(2, newPlayer.appId);
            statement.setString(3, newPlayer.name);
            statement.setLong(4, newPlayer.exp);
            statement.setInt(5, newPlayer.level);
            statement.setInt(6, newPlayer.money);
            statement.executeUpdate();

            // Retrieve the newly created player to get the generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newPlayer.id = generatedKeys.getLong(1);
                }
            }
            return newPlayer;
        }
    }

    @Override
    public Food getFood(String name) {
        String selectSql = SQLQueries.SELECT_FOOD_BY_NAME;
        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return FOO;
            return createPlayer(appType, appId, connection);
        } catch (SQLException e) {
            // log
            return new Player();
        }
    }

    @Override
    public Work getWork(String name) {
        return null;
    }

    @Override
    public List<Food> getAllFood() {
        return List.of();
    }

    @Override
    public List<Work> getAllWork() {
        return List.of();
    }

    @Override
    public boolean updatePlayerExp(AppType appType, long id, long deltaExp) {
        String updateSql = SQLQueries.UPDATE_PLAYER_EXP;
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setLong(1, deltaExp);
            statement.setLong(2, appType.ordinal());
            statement.setLong(3, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updatePlayerMoney(AppType appType, long id, int deltaMoney) {
        String updateSql = SQLQueries.UPDATE_PLAYER_MONEY;
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setInt(1, deltaMoney);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            // logs
            return false;
        }
    }

    @Override
    public boolean updatePlayerExpAndMoney(AppType appType, long id, long deltaExp, int deltaMoney) {
        String updateSql = SQLQueries.UPDATE_PLAYER;
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setLong(1, deltaExp);
            statement.setInt(2, deltaMoney);
            statement.setLong(3, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            // log
            return false;
        }
    }

}

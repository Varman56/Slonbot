package our.slonbot.connection;

import our.slonbot.model.AppType;
import our.slonbot.model.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLiteWorker implements IDataWorker {

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/slonbot.db";

    public SQLiteWorker() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            try (PreparedStatement statement = connection.prepareStatement(SQLQueries.CREATE_TABLE)) {
                statement.execute();
            }
        } catch (SQLException e) {
            // log error
        }
    }

    @Override
    public Player getPlayer(AppType appType, int appId) {
        String selectSql = SQLQueries.SELECT_BY_APP_TYPE_AND_APP_ID;
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
                statement.setString(1, appType.name());
                statement.setInt(2, appId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return  new Player(
                            resultSet.getLong("id"),
                            AppType.valueOf(resultSet.getString("app_type")),
                            resultSet.getInt("app_id"),
                            resultSet.getString("name"),
                            resultSet.getLong("exp"),
                            resultSet.getInt("level"),
                            resultSet.getInt("money")
                    );
                }
            }
            // If player not found, create a new one
            return createPlayer(appType, appId, connection);
        } catch (SQLException e) {
            // log
            return null;
        }
    }

    private Player createPlayer(AppType appType, int appId, Connection connection) throws SQLException {
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

            statement.setString(1, newPlayer.appType.name());
            statement.setInt(2, newPlayer.appId);
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
    public boolean updatePlayerExp(long id, long deltaExp) {
        String updateSql = SQLQueries.UPDATE_PLAYER_EXP;
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setLong(1, deltaExp);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            // log
            return false;
        }
    }

    @Override
    public boolean updatePlayerMoney(long id, int deltaMoney) {
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
    public boolean updatePlayerExpAndMoney(long id, long deltaExp, int deltaMoney) {
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

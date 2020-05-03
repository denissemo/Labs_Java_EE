package DAO;

import DB.DBConnect;
import logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CityDao {
    private final Connection connect;
    private final Logger logger;

    public CityDao(DBConnect connect) {
        this.logger = new Logger();
        this.connect = connect.getConn();
    }

    public void insert(String name) throws SQLException {
        PreparedStatement statement = connect.prepareStatement("INSERT INTO city VALUES (DEFAULT, ?)");

        try {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void update(String cityId, String name) throws SQLException {
        PreparedStatement statement = connect.prepareStatement("UPDATE city SET name = ? WHERE id = ?");

        try {
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(cityId));
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void delete(String cityId) throws SQLException {
        PreparedStatement statement = connect.prepareStatement("DELETE FROM city WHERE id = ?");

        try {
            statement.setInt(1, Integer.parseInt(cityId));
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}

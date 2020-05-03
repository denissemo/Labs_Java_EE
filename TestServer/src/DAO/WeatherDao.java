package DAO;

import DB.DBConnect;
import logger.Logger;
import models.CityStation;
import models.Weather;
import org.postgresql.core.SqlCommand;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

public class WeatherDao {
    private static final String SQL_FIND_WEATHER =
            "select date, c.name  as city, s2.name as stationName, " +
                    "mintemp, maxtemp, wind, description, cloud " +
                    "from forecast " +
                    "inner join city c on forecast.cityid = c.id " +
                    "inner join sensor s on forecast.\"sensorId\" = s.id " +
                    "inner join station s2 on s.\"stationId\" = s2.id where 1=1 %s %s";
    private final Connection connect;
    private final Logger logger;

    public WeatherDao(DBConnect connect) {
        this.logger = new Logger();
        this.connect = connect.getConn();
    }

    public ArrayList<Weather> get(String where, String order) throws SQLException {
        Statement statement = connect.createStatement();

        ArrayList<Weather> result = new ArrayList<>();

        try {
            logger.info(String.format("Execute sql: %s", String.format(SQL_FIND_WEATHER, where, order)));

            ResultSet rs = statement.executeQuery(String.format(SQL_FIND_WEATHER, where, order));

            while (rs.next()) {
                String date = rs.getString("date");
                String city = rs.getString("city");
                String stationName = rs.getString("stationName");
                String minTemp = rs.getString("mintemp");
                String maxTemp = rs.getString("maxtemp");
                String wind = rs.getString("wind");
                String description = rs.getString("description");
                String cloud = rs.getString("cloud");

                // Formatting date from DB
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String strDate = dateFormat.format(parsedDate);

                Weather item = new Weather(strDate, city, stationName, minTemp, maxTemp, wind, description, cloud);

                result.add(item);
            }
            rs.close();
        } catch (NumberFormatException | ParseException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    public ArrayList<CityStation> getSelectInfo(String table) throws SQLException {
        Statement statement = connect.createStatement();

        ArrayList<CityStation> result = new ArrayList<>();

        try {
            String sql = String.format("SELECT id, name FROM %s", table);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");

                CityStation item = new CityStation(id, name);

                result.add(item);
            }

            rs.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }
}

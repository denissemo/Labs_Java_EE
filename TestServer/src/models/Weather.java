package models;

import java.io.Serializable;

public class Weather implements Serializable {
    private static final long serialVersionUID = 1L;

    public String date;
    public String city;
    public String stationName;
    public String minTemp;
    public String maxTemp;
    public String wind;
    public String description;
    public String cloud;

    public Weather(String date, String city, String stationName, String minTemp, String maxTemp,
                   String wind, String description, String cloud) {
        this.date = date;
        this.city = city;
        this.stationName = stationName;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.wind = wind;
        this.description = description;
        this.cloud = cloud;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getStationName() {
        return stationName;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getWind() {
        return wind;
    }

    public String getDescription() {
        return description;
    }

    public String getCloud() {
        return cloud;
    }
//    private static final String SQL_FIND_WEATHER =
//            "select date, c.name  as city, s2.name as stationName, " +
//                "mintemp, maxtemp, wind, description, cloud " +
//            "from forecast " +
//                "inner join city c on foforecast.\"sensorId\" = s.id " +
//                "inner join station s2 on s.\"stationId\" = s2.id where 1=1 %s %s";
//    private final Connection connect;recast.cityid = c.id " +
////                "inner join sensor s on
//    private final Logger logger;
//
//    public Weather(DBConnect connect) {
//        this.logger = new Logger();
//        this.connect = connect.getConn();
//    }
//
//    public ArrayList<Map<String, String>> getWeather(String where, String order) throws SQLException {
//        Statement statement = connect.createStatement();
//
//        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
//
//        try {
//            logger.info(String.format("Execute sql: %s", String.format(SQL_FIND_WEATHER, where, order)));
//
//            ResultSet rs = statement.executeQuery(String.format(SQL_FIND_WEATHER, where, order));
//
//            while (rs.next()) {
//                String date = rs.getString("date");
//                String city = rs.getString("city");
//                String stationName = rs.getString("stationName");
//                String minTemp = rs.getString("mintemp");
//                String maxTemp = rs.getString("maxtemp");
//                String wind = rs.getString("wind");
//                String description = rs.getString("description");
//                String cloud = rs.getString("cloud");
//
//                Map<String, String> row = new HashMap<String, String>();
//
//                // Formatting date from DB
//                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//                String strDate = dateFormat.format(parsedDate);
//
//                row.put("date", strDate);
//                row.put("city", city);
//                row.put("stationName", stationName);
//                row.put("minTemp", minTemp);
//                row.put("maxTemp", maxTemp);
//                row.put("wind", wind);
//                row.put("description", description);
//                row.put("cloud", cloud);
//
//                result.add(row);
//            }
//            rs.close();
//        } catch (NumberFormatException | ParseException e) {
//            logger.error(e.getMessage());
//        }
//
//        return result;
//    }
//
//    public ArrayList<Map<String, String>> getSelectInfo(String table) throws SQLException {
//        Statement statement = connect.createStatement();
//
//        String sql = String.format("select id, name from %s", table);
//        ResultSet rs = statement.executeQuery(sql);
//
//        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
//
//        while (rs.next()) {
//            String id = rs.getString("id");
//            String name = rs.getString("name");
//            Map<String, String> row = new HashMap<String, String>();
//
//            row.put("id", id);
//            row.put("name", name);
//
//            result.add(row);
//        }
//
//        rs.close();
//
//        return result;
//    }
}

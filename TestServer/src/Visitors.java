import DB.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Visitors {
    DBConnect conn;

    public Visitors(DBConnect conn) throws SQLException {
        this.conn = conn;
    }

    public void save(String ipAddr) throws SQLException {
        try {
            this.conn.query(String.format("INSERT INTO visitor values (DEFAULT, '%s') ON CONFLICT DO NOTHING", ipAddr));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count() throws SQLException {
        ResultSet rs = this.conn.query("SELECT count(id) from visitor");
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("count");
        }
        rs.close();

        return count;
    }
}
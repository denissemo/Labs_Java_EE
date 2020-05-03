package DB;

import java.sql.*;
import java.util.Map;


public class DBConnect {
    private boolean connected = false;
    public Statement stmt;
    private Connection conn = null;

    public DBConnect() throws SQLException {
        Map<String, String> env = System.getenv();
        String username = env.get("db_user");
        String password = env.get("db_password");
        String host = env.get("db_host");
        String name = env.get("db_name");
        String DB_URL = String.format("jdbc:postgresql://%s/%s", host, name);

        try {
            this.conn = DriverManager.getConnection(DB_URL, username, password);
            this.connected = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void close() throws SQLException {
        this.conn.close();
    }

    public ResultSet query(String sql) throws SQLException, org.postgresql.util.PSQLException {
        this.stmt = this.conn.createStatement();
        return this.stmt.executeQuery(sql);

    }

    public Connection getConn() {
        return this.conn;
    }
}

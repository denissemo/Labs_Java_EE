package logger;

import java.sql.Timestamp;

public class Logger {
    public void info(String message) {
        System.out.println(String.format("%s [INFO] %s", getTimestamp(), message));
    }

    public void error(String message) {
        System.err.println(String.format("%s [ERROR] %s", getTimestamp(), message));
    }

    private Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}

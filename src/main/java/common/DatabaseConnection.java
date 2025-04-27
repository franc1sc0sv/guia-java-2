package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class DatabaseConnection {

    private static final String AGENDA_URL = "jdbc:postgresql://localhost:5432/agenda";
    private static final String CARCLEAN_URL = "jdbc:postgresql://localhost:5434/carclean";

    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getAgendaConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(AGENDA_URL, USER, PASSWORD);
    }

    public static Connection getCarCleanConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(CARCLEAN_URL, USER, PASSWORD);
    }
}

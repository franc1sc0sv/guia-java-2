package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String AGENDA_URL = "jdbc:postgresql://localhost:5432/agenda";
    private static final String CARCLEAN_URL = "jdbc:postgresql://localhost:5434/carclean";

    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    static {
        try {
            Class.forName("org.postgresql.Driver"); // Asegura que el driver esté cargado
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el Driver de PostgreSQL", e);
        }
    }

    public static Connection getAgendaConnection() throws SQLException {
        return DriverManager.getConnection(AGENDA_URL, USER, PASSWORD);
    }

    public static Connection getCarCleanConnection() throws SQLException {
        return DriverManager.getConnection(CARCLEAN_URL, USER, PASSWORD);
    }
}

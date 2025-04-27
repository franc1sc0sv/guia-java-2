package ejercicio3.beans;

import common.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Contactolista {

    private static Connection getConnection() throws SQLException {
        return DatabaseConnection.getAgendaConnection();
    }

    public static void agregarContacto(Contacto contacto) throws SQLException {
        String query = "INSERT INTO contacto (nombre, telefono, correo) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, contacto.getNombre());
            stmt.setString(2, contacto.getTelefono());
            stmt.setString(3, contacto.getCorreo());
            stmt.executeUpdate();
        }
    }

    public static List<Contacto> obtenerContactos() throws SQLException {
        List<Contacto> listaContactos = new ArrayList<>();
        String query = "SELECT id, nombre, telefono, correo FROM contacto";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Contacto contacto = new Contacto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                listaContactos.add(contacto);
            }
        }
        return listaContactos;
    }

    public static Contacto buscarContacto(String nombre) throws SQLException {
        String query = "SELECT id, nombre, telefono, correo FROM contacto WHERE nombre = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Contacto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
            }
        }
        return null;
    }
}

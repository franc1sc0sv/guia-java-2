package ejercicio5.services;

import common.DatabaseConnection;
import ejercicio5.beans.Cliente;
import ejercicio5.beans.Servicio;
import ejercicio5.beans.Automotor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    public static void insertarCliente(Cliente cliente) throws SQLException {
        String insertAutomotorSQL = "INSERT INTO automotor (marca, modelo, year) VALUES (?, ?, ?) RETURNING id";
        String insertClienteSQL = "INSERT INTO cliente (nombres, apellidos, vip, id_servicio, id_automotor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getCarCleanConnection();
             PreparedStatement automotorStmt = conn.prepareStatement(insertAutomotorSQL);
             PreparedStatement clienteStmt = conn.prepareStatement(insertClienteSQL)) {

            // Insertar automotor
            automotorStmt.setString(1, cliente.getAutomotor().getMarca());
            automotorStmt.setString(2, cliente.getAutomotor().getModelo());
            automotorStmt.setInt(3, cliente.getAutomotor().getYear());

            ResultSet rs = automotorStmt.executeQuery();
            int idAutomotor = 0;
            if (rs.next()) {
                idAutomotor = rs.getInt(1);
            }

            // Insertar cliente
            clienteStmt.setString(1, cliente.getNombres());
            clienteStmt.setString(2, cliente.getApellidos());
            clienteStmt.setBoolean(3, cliente.isVip());
            clienteStmt.setInt(4, cliente.getServicio().getId());
            clienteStmt.setInt(5, idAutomotor);

            clienteStmt.executeUpdate();
        }
    }

    public static List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT c.id, c.nombres, c.apellidos, c.vip, " +
                "s.id as servicio_id, s.tipo, s.precio, " +
                "a.id as automotor_id, a.marca, a.modelo, a.year " +
                "FROM cliente c " +
                "JOIN servicio s ON c.id_servicio = s.id " +
                "JOIN automotor a ON c.id_automotor = a.id " +
                "ORDER BY c.id ASC";


        try (Connection conn = DatabaseConnection.getCarCleanConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servicio servicio = new Servicio(
                        rs.getInt("servicio_id"),
                        rs.getString("tipo"),
                        rs.getDouble("precio")
                );

                Automotor automotor = new Automotor(
                        rs.getInt("automotor_id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("year")
                );

                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getBoolean("vip"),
                        servicio,
                        automotor
                );

                clientes.add(cliente);
            }
        }

        return clientes;
    }
}

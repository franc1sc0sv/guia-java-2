package ejercicio5.servlets;

import ejercicio5.beans.Automotor;
import ejercicio5.beans.Cliente;
import ejercicio5.beans.Servicio;
import ejercicio5.services.ClienteService;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ejercicio5/cliente")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String vipParam = request.getParameter("vip");
        boolean vip = "on".equals(vipParam);
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String yearParam = request.getParameter("year");
        String idServicioParam = request.getParameter("idServicio");

        String error = null;

        if (nombres == null || nombres.trim().isEmpty()) {
            error = "El campo 'Nombres' es obligatorio.";
        } else if (apellidos == null || apellidos.trim().isEmpty()) {
            error = "El campo 'Apellidos' es obligatorio.";
        } else if (marca == null || marca.trim().isEmpty()) {
            error = "El campo 'Marca' del vehículo es obligatorio.";
        } else if (modelo == null || modelo.trim().isEmpty()) {
            error = "El campo 'Modelo' del vehículo es obligatorio.";
        } else if (yearParam == null || yearParam.trim().isEmpty()) {
            error = "El campo 'Año' es obligatorio.";
        } else if (idServicioParam == null || idServicioParam.trim().isEmpty()) {
            error = "Debe seleccionar un 'Tipo de Servicio'.";
        } else {
            try {
                int year = Integer.parseInt(yearParam);
                if (year < 1990 || year > 2025) {
                    error = "El año del vehículo debe estar entre 2000 y 2025.";
                }
            } catch (NumberFormatException e) {
                error = "El año debe ser un número válido.";
            }
        }

        if (error != null) {
            request.setAttribute("error", error);
            try {
                List<Cliente> clientes = ClienteService.listarClientes();
                request.setAttribute("clientes", clientes);
            } catch (SQLException e) {
                throw new ServletException("Error al listar clientes", e);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ejercicio5/Ejercicio5.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int year = Integer.parseInt(yearParam);
            int idServicio = Integer.parseInt(idServicioParam);

            Servicio servicio = new Servicio();
            servicio.setId(idServicio);

            Automotor automotor = new Automotor();
            automotor.setMarca(marca);
            automotor.setModelo(modelo);
            automotor.setYear(year);

            Cliente cliente = new Cliente();
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            cliente.setVip(vip);
            cliente.setServicio(servicio);
            cliente.setAutomotor(automotor);

            ClienteService.insertarCliente(cliente);

            response.sendRedirect(request.getContextPath() + "/ejercicio5/cliente");

        } catch (SQLException e) {
            throw new ServletException("Error al insertar cliente", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Cliente> clientes = ClienteService.listarClientes();
            request.setAttribute("clientes", clientes);
        } catch (SQLException e) {
            throw new ServletException("Error al listar clientes", e);
        }

        request.getRequestDispatcher("/pages/ejercicio5/Ejercicio5.jsp").forward(request, response);
    }

}

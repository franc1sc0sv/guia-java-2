package ejercicio3.beans;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ContactoServlet")
public class ContactoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("mostrar".equals(action)) {
            mostrarContactos(request, response);
        } else if ("buscar".equals(action)) {
            buscarContacto(request, response);
        } else {
            mostrarFormulario(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("agregar".equals(action)) {
            agregarContacto(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
    }

    private void mostrarContactos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Contacto> listaContactos = Contactolista.obtenerContactos();
            request.setAttribute("contactos", listaContactos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mostrarContactos.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener contactos", e);
        }
    }

    private void buscarContacto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        try {
            Contacto contacto = Contactolista.buscarContacto(nombre);
            if (contacto != null) {
                request.setAttribute("contacto", contacto);
            } else {
                request.setAttribute("error", "Contacto no encontrado.");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/buscarContacto.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al buscar contacto", e);
        }
    }

    private void agregarContacto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        try {
            Contacto contacto = new Contacto(nombre, telefono, correo);
            Contactolista.agregarContacto(contacto);
            response.sendRedirect("ContactoServlet?action=mostrar"); // Redirige para mostrar los contactos
        } catch (SQLException e) {
            throw new ServletException("Error al agregar contacto", e);
        }
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/formularioContacto.jsp");
        dispatcher.forward(request, response);
    }
}

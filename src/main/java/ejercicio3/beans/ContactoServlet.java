package ejercicio3.beans;

import ejercicio3.services.AgendaService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ContactoServlet")
public class ContactoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AgendaService agendaService;

    @Override
    public void init() throws ServletException {
        super.init();
        agendaService = new AgendaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("ver".equals(action)) {
            mostrarContactos(request, response);
        } else if ("buscar".equals(action)) {
            mostrarFormularioBusqueda(request, response);
        } else {
            mostrarFormularioAgregar(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("agregar".equals(action)) {
            agregarContacto(request, response);
        } else if ("buscarContacto".equals(action)) {
            buscarContacto(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
    }

    private void mostrarContactos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Contacto> listaContactos = agendaService.listarContactos();
            request.setAttribute("contactos", listaContactos);
            request.getRequestDispatcher("/pages/ejercicio3/Ejercicio3.jsp?accion=ver").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener contactos", e);
        }
    }

    private void buscarContacto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        try {
            Contacto contacto = agendaService.buscarContactoPorNombre(nombre);
            if (contacto != null) {
                request.setAttribute("contacto", contacto);
            } else {
                request.setAttribute("error", "Contacto no encontrado.");
            }
            request.getRequestDispatcher("/pages/ejercicio3/Ejercicio3.jsp?accion=buscar").forward(request, response);
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
            agendaService.agregarContacto(contacto);
            response.sendRedirect("ContactoServlet?action=ver");
        } catch (SQLException | IllegalArgumentException e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/pages/ejercicio3/Ejercicio3.jsp?accion=agregar").forward(request, response);
        }
    }

    private void mostrarFormularioAgregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("pages/ejercicio3/Ejercicio3.jsp?accion=agregar");
    }

    private void mostrarFormularioBusqueda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("pages/ejercicio3/Ejercicio3.jsp?accion=buscar");
    }
}

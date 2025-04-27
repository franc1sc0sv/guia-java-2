package ejercicio1.Servlet; // Declaramos que esta clase pertenece al paquete `ejercicio1.Servlet`

import ejercicio1.beans.Estudiante; // Importamos la clase Estudiante
import ejercicio1.services.EstudianteService; // Importamos el servicio de validación de estudiantes

import jakarta.servlet.*; // Importamos la clase Servlet y otras interfaces necesarias de Jakarta EE
import jakarta.servlet.annotation.WebServlet; // Importamos la anotación WebServlet
import jakarta.servlet.http.*; // Importamos clases de Servlet HTTP
import java.io.IOException; // Importamos IOException para manejar excepciones de entrada/salida

@WebServlet("/ejercicio1/submit") // Anotación que mapea esta clase como un servlet a la URL /ejercicio1/submit
public class Ejercicio1Servlet extends HttpServlet {
    // Metodo que maneja las solicitudes POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos una nueva instancia de Estudiante
        Estudiante est = new Estudiante();
        // Establecemos los atributos del estudiante usando los parámetros del formulario
        est.setCarnet(request.getParameter("carnet"));
        est.setNombres(request.getParameter("nombres"));
        est.setApellidos(request.getParameter("apellidos"));
        est.setDireccion(request.getParameter("direccion"));
        est.setTelefono(request.getParameter("telefono"));
        est.setEmail(request.getParameter("email"));
        est.setFechaNacimiento(request.getParameter("fechaNacimiento"));

        // Validamos los datos del estudiante
        String error = EstudianteService.validarEstudiante(est);

        // Si hay un error, lo establecemos como atributo de la solicitud
        if (error != null) {
            request.setAttribute("error", error); // Guardamos el mensaje de error para mostrarlo en la vista
        } else {
            request.setAttribute("estudiante", est); // Guardamos el objeto Estudiante si no hay errores
        }

        // Redireccionamos a la página correspondiente para mostrar resultados
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ejercicio1/Ejercicio1.jsp");
        dispatcher.forward(request, response); // Realizamos la redirección
    }
}
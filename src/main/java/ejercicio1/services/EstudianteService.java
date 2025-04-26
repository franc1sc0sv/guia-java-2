package ejercicio1.services; // Declaramos que esta clase pertenece al paquete `ejercicio1.services`

import ejercicio1.beans.Estudiante; // Importamos la clase Estudiante del paquete `ejercicio1.beans`
import java.util.regex.Pattern; // Importamos la clase Pattern para trabajar con expresiones regulares

public class EstudianteService {

    // Método estático que valida los datos de un estudiante
    public static String validarEstudiante(Estudiante est) {

        // Validación del carnet
        if (est.getCarnet() == null || !est.getCarnet().matches("^[A-Za-z]{2}\\d{4}$"))
            return "Carnet inválido. Debe tener dos letras y cuatro números.";

        // Validación de los nombres
        if (est.getNombres() == null || est.getNombres().length() > 25)
            return "Nombre inválido. Máximo 25 caracteres.";

        // Validación de los apellidos
        if (est.getApellidos() == null || est.getApellidos().length() > 25)
            return "Apellido inválido. Máximo 25 caracteres.";

        // Validación de la dirección
        if (est.getDireccion() == null || est.getDireccion().length() > 255)
            return "Dirección inválida. Máximo 255 caracteres.";

        // Validación del teléfono
        if (est.getTelefono() == null || !est.getTelefono().matches("^\\d{4}-\\d{4}$"))
            return "Teléfono inválido. Debe tener formato 0000-0000.";

        // Validación del correo electrónico
        if (est.getEmail() == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", est.getEmail()))
            return "Correo electrónico inválido.";

        // Validación de la fecha de nacimiento
        if (est.getFechaNacimiento() == null || !est.getFechaNacimiento().matches("^\\d{2}/\\d{2}/\\d{4}$"))
            return "Fecha de nacimiento inválida. Debe tener formato DD/MM/YYYY.";

        return null; // Si no hay errores, retornamos null indicando que la validación fue exitosa
    }
}
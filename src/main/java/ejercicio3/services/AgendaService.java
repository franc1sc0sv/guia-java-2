package ejercicio3.services;

import ejercicio3.beans.Contacto;
import ejercicio3.beans.Contactolista;
import java.sql.SQLException;
import java.util.List;

public class AgendaService {

    public void agregarContacto(Contacto contacto) throws SQLException {
        if (contacto.getNombre() == null || contacto.getTelefono() == null || contacto.getCorreo() == null ||
                contacto.getNombre().isEmpty() || contacto.getTelefono().isEmpty() || contacto.getCorreo().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
        Contactolista.agregarContacto(contacto);
    }

    public List<Contacto> listarContactos() throws SQLException {
        return Contactolista.obtenerContactos();
    }

    public Contacto buscarContactoPorNombre(String nombre) throws SQLException {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre para buscar.");
        }
        return Contactolista.buscarContacto(nombre);
    }
}

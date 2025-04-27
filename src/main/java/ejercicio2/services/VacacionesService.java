package ejercicio2.services;

import ejercicio2.beans.Empleado;
import java.time.LocalDate;
import java.time.Period;

public class VacacionesService {

    // Calcula los días de vacaciones según los años trabajados
    public int calcularDiasVacaciones(Empleado empleado) {
        LocalDate fechaActual = LocalDate.now(); // Fecha actual
        Period periodo = Period.between(empleado.getFechaIngreso(), fechaActual); // Diferencia de tiempo
        int aniosTrabajados = periodo.getYears(); // Años trabajados

        // Asignación de días de vacaciones según antigüedad
        if (aniosTrabajados >= 1 && aniosTrabajados < 3) {
            return 10;
        } else if (aniosTrabajados >= 3 && aniosTrabajados < 5) {
            return 15;
        } else if (aniosTrabajados >= 5) {
            return 21;
        } else {
            return 0; // Menos de 1 año, no aplica
        }
    }
}

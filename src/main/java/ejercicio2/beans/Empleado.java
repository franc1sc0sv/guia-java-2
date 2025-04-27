package ejercicio2.beans;

import java.time.LocalDate;

public class Empleado {
    // Atributos del empleado
    private String nombre;
    private String apellido;
    private double salario;
    private LocalDate fechaIngreso;

    // Constructor vacío
    public Empleado() {
    }

    // Constructor con todos los atributos
    public Empleado(String nombre, String apellido, double salario, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    // Getter y Setter para el nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para el apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter y Setter para el salario
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // Getter y Setter para la fecha de ingreso
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}

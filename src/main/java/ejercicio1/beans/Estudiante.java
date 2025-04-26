package ejercicio1.beans; // Declaramos que esta clase pertenece al paquete `ejercicio1.beans`

public class Estudiante {
    // Atributos privados de la clase Estudiante
    private String carnet;           // Identificación del estudiante
    private String nombres;          // Nombres del estudiante
    private String apellidos;        // Apellidos del estudiante
    private String direccion;        // Dirección del estudiante
    private String telefono;         // Número de teléfono del estudiante
    private String email;            // Correo electrónico del estudiante
    private String fechaNacimiento;  // Fecha de nacimiento del estudiante

    // Métodos getter y setter para acceder y modificar los atributos
    public String getCarnet() {
        return carnet;
    } // Retorna el valor del atributo `carnet`

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    } // Establece el valor del atributo `carnet`

    public String getNombres() {
        return nombres;
    } // Retorna el valor del atributo `nombres`

    public void setNombres(String nombres) {
        this.nombres = nombres;
    } // Establece el valor del atributo `nombres`

    public String getApellidos() {
        return apellidos;
    } // Retorna el valor del atributo `apellidos`

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    } // Establece el valor del atributo `apellidos`

    public String getDireccion() {
        return direccion;
    } // Retorna el valor del atributo `direccion`

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    } // Establece el valor del atributo `direccion`

    public String getTelefono() {
        return telefono;
    } // Retorna el valor del atributo `telefono`

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    } // Establece el valor del atributo `telefono`

    public String getEmail() {
        return email;
    } // Retorna el valor del atributo `email`

    public void setEmail(String email) {
        this.email = email;
    } // Establece el valor del atributo `email`

    public String getFechaNacimiento() {
        return fechaNacimiento;
    } // Retorna el valor del atributo `fechaNacimiento`

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    } // Establece el valor del atributo `fechaNacimiento`
}
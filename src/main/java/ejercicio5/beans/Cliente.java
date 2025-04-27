package ejercicio5.beans;

import ejercicio5.interfaces.ITotalPago;

public class Cliente implements ITotalPago {
    private int id;
    private String nombres;
    private String apellidos;
    private boolean vip;
    private Servicio servicio;
    private Automotor automotor;

    public Cliente() {}

    public Cliente(int id, String nombres, String apellidos, boolean vip, Servicio servicio, Automotor automotor) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.vip = vip;
        this.servicio = servicio;
        this.automotor = automotor;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }
    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public Automotor getAutomotor() { return automotor; }
    public void setAutomotor(Automotor automotor) { this.automotor = automotor; }

    // Implementación de la Interface ITotalPago
    @Override
    public double totalPago() {
        if (vip) {
            return servicio.getPrecio() * 0.85; // 15% descuento
        } else {
            return servicio.getPrecio();
        }
    }
}

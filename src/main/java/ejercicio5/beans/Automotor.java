package ejercicio5.beans;

public class Automotor {
    private int id;
    private String marca;
    private String modelo;
    private int year;

    public Automotor() {}

    public Automotor(int id, String marca, String modelo, int year) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}

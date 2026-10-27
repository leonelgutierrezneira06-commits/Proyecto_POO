package com.mycompany.pe.edu.utp.proyecto_poo;

public class DISPOSITIVO {
    private int id;
    private String nombre;
    private String tipo;
    private double consumoVatios;

    public DISPOSITIVO() {}

    public DISPOSITIVO(int id, String nombre, String tipo, double consumoVatios) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.consumoVatios = consumoVatios;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getConsumoVatios() { return consumoVatios; }
    public void setConsumoVatios(double consumoVatios) { this.consumoVatios = consumoVatios; }
}

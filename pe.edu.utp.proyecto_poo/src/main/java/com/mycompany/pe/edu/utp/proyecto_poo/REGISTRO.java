package com.mycompany.pe.edu.utp.proyecto_poo;

public class REGISTRO {
    private int id;
    private int dispositivoId;
    private String fechaHora;
    private double consumoKwh;

    public REGISTRO() {}

    public REGISTRO(int id, int dispositivoId, String fechaHora, double consumoKwh) {
        this.id = id;
        this.dispositivoId = dispositivoId;
        this.fechaHora = fechaHora;
        this.consumoKwh = consumoKwh;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDispositivoId() { return dispositivoId; }
    public void setDispositivoId(int dispositivoId) { this.dispositivoId = dispositivoId; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public double getConsumoKwh() { return consumoKwh; }
    public void setConsumoKwh(double consumoKwh) { this.consumoKwh = consumoKwh; }
}
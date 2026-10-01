package com.mycompany.pe.edu.utp.proyecto_poo;

public class EntradaEnergia {

    private double consumo;
    private double temperatura;
    private double carga;
    private String tipoDispositivo;
    
    public EntradaEnergia(double consumo, double temperatura, double carga, String tipoDispositivo) {
        this.consumo = consumo;
        this.temperatura = temperatura;
        this.carga = carga;
        this.tipoDispositivo = tipoDispositivo;
    }
    
    public double getConsumo() {
        return consumo;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getCarga() {
        return carga;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }
}
 
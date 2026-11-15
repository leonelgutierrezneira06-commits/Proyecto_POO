package com.mycompany.pe.edu.utp.proyecto_poo;

public class ResultadoEnergia {
  
    private double consumoOriginal;
    private double consumoFinal;
    private double porcentajeAhorro;
    private String accion;
    private String estrategiaId;
    
    public ResultadoEnergia(double consumoOriginal, double consumoFinal, double porcentajeAhorro, String accion, String estrategiaId) {
        
        this.consumoOriginal = consumoOriginal;
        this.consumoFinal = consumoFinal;
        this.porcentajeAhorro = porcentajeAhorro;
        this.accion = accion;
        this.estrategiaId = estrategiaId;
    } 
    
    public double getConsumoOriginal() {
        return consumoOriginal;
    }

    public double getConsumoFinal() {
        return consumoFinal;
    }

    public double getPorcentajeAhorro() {
        return porcentajeAhorro;
    }

    public String getAccion() {
        return accion;
    }

    public String getEstrategiaId() {
        return estrategiaId;
    }
}

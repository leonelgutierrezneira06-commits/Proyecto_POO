package com.mycompany.pe.edu.utp.proyecto_poo;

public class EstrategiaEcoEnergia implements Estrategia<EntradaEnergia, ResultadoEnergia> {

    @Override
    public String id() {
        return "ECO_ENERGIA";
    }

    @Override
    public boolean soporta(Object contexto) {
        return true;
    }

    @Override
    public ResultadoEnergia ejecutar(EntradaEnergia entrada) {
        
        double consumoActual = entrada.getConsumo();
        double temperatura = entrada.getTemperatura();
        double carga = entrada.getCarga();
        
        double porcentajeAhorro;

        if (carga < 40) {
            porcentajeAhorro = 0.30;
        } else if (carga < 70) {
            porcentajeAhorro = 0.20;
        } else {
            porcentajeAhorro = 0.10;
        }
        
        double ahorroObtenido = consumoActual * porcentajeAhorro;
        double consumoFinal = consumoActual - ahorroObtenido;
        
        String accion = "Reducir consumo para mejorar el ahorro energetico";
        
        return new ResultadoEnergia(consumoActual, consumoFinal, porcentajeAhorro * 100, accion, id());
    }
}
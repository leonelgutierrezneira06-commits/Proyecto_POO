/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pe.edu.utp.proyecto_poo;

/**
 *
 * @author ASUS
 */
public class PeEduUtpProyecto_poo {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ConexionSQLite.crearTablasIniciales();
        
        EntradaEnergia entrada = new EntradaEnergia(
            750,
            68,
            50,
            "PC Escritorio"
        );

        EstrategiaEcoEnergia estrategia = new EstrategiaEcoEnergia();

        ResultadoEnergia resultado = estrategia.ejecutar(entrada);

        System.out.println("=== PRUEBA CONTROL ENERGETICO ===");
        System.out.println("Estrategia: " + resultado.getEstrategiaId());
        System.out.println("Consumo original: " + resultado.getConsumoOriginal() + " W");
        System.out.println("Consumo final: " + resultado.getConsumoFinal() + " W");
        System.out.println("Ahorro: " + resultado.getPorcentajeAhorro() + "%");
        System.out.println("Accion: " + resultado.getAccion());
    }
}

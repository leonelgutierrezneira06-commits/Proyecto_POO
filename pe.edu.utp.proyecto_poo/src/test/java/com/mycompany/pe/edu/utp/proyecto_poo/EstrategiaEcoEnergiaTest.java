package com.mycompany.pe.edu.utp.proyecto_poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstrategiaEcoEnergiaTest {

    @Test
    public void probarAhorroCargaMedia() {

        EntradaEnergia entrada = new EntradaEnergia(
                750,
                68,
                50,
                "PC Escritorio"
        );

        EstrategiaEcoEnergia estrategia = new EstrategiaEcoEnergia();

        ResultadoEnergia resultado = estrategia.ejecutar(entrada);

        assertEquals(750.0, resultado.getConsumoOriginal(), 0.01);
        assertEquals(600.0, resultado.getConsumoFinal(), 0.01);
        assertEquals(20.0, resultado.getPorcentajeAhorro(), 0.01);
        assertEquals("ECO_ENERGIA", resultado.getEstrategiaId());
    }
}
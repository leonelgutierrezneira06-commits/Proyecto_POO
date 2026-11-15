
package com.mycompany.pe.edu.utp.proyecto_poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pe.edu.utp.proyecto_poo.plugins.java.PerformanceEnergyStrategy;

public class AdaptadorEstrategiaGuillermoTest {

    @Test
    public void probarIntegracionConGuillermo() {

        AdaptadorEstrategiaGuillermo adaptador =
            new AdaptadorEstrategiaGuillermo(
                new PerformanceEnergyStrategy()
            );

        EntradaEnergia entrada = new EntradaEnergia(
            750,
            68,
            80,
            "PC Escritorio"
        );

        assertTrue(adaptador.soporta(entrada));

        ResultadoEnergia resultado = adaptador.ejecutar(entrada);

        assertEquals("performance", resultado.getEstrategiaId());
        assertEquals(750.0, resultado.getConsumoOriginal(), 0.001);
        assertEquals(712.5, resultado.getConsumoFinal(), 0.001);
        assertEquals(5.0, resultado.getPorcentajeAhorro(), 0.001);

        System.out.println("Integracion exitosa con Guillermo");
        System.out.println("Consumo final: "
                + resultado.getConsumoFinal() + " W");
    }

    @Test
    public void probarEntradaNoAdmitida() {

        AdaptadorEstrategiaGuillermo adaptador =
            new AdaptadorEstrategiaGuillermo(
                new PerformanceEnergyStrategy()
            );

        EntradaEnergia entrada = new EntradaEnergia(
            750, 68, 50, "PC Escritorio"
        );

        assertFalse(adaptador.soporta(entrada));

        assertThrows(IllegalArgumentException.class,
                () -> adaptador.ejecutar(entrada));
    }
}

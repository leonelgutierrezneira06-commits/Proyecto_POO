
package com.mycompany.pe.edu.utp.proyecto_poo;

import java.util.Objects;
import pe.edu.utp.proyecto_poo.api.Context;
import pe.edu.utp.proyecto_poo.api.Result;
import pe.edu.utp.proyecto_poo.api.Strategy;
import pe.edu.utp.proyecto_poo.domain.EnergyInput;
import pe.edu.utp.proyecto_poo.domain.EnergyResult;

public final class AdaptadorEstrategiaGuillermo
        implements Estrategia<EntradaEnergia, ResultadoEnergia> {

    private final Strategy<EnergyInput, EnergyResult> estrategiaGuillermo;

    public AdaptadorEstrategiaGuillermo(
            Strategy<EnergyInput, EnergyResult> estrategiaGuillermo) {
        this.estrategiaGuillermo = Objects.requireNonNull(
                estrategiaGuillermo,
                "La estrategia de Guillermo es obligatoria");
    }

    @Override
    public String id() {
        return estrategiaGuillermo.id();
    }

    @Override
    public boolean soporta(Object contexto) {
        if (contexto instanceof EntradaEnergia entrada) {
            return estrategiaGuillermo.supports(
                    convertirContexto(entrada));
        }

        if (contexto instanceof Context contextoGuillermo) {
            return estrategiaGuillermo.supports(contextoGuillermo);
        }

        return false;
    }

    @Override
    public ResultadoEnergia ejecutar(EntradaEnergia entrada) {
        if (entrada == null) {
            throw new IllegalArgumentException(
                    "La entrada de energia no puede ser nula.");
        }

        if (!soporta(entrada)) {
            throw new IllegalArgumentException(
                    "La estrategia '" + id()
                    + "' no admite estos datos.");
        }

        EnergyInput entradaGuillermo = new EnergyInput(
                entrada.getConsumo(),
                entrada.getTemperatura(),
                entrada.getCarga(),
                entrada.getTipoDispositivo()
        );

        Result<EnergyResult> respuesta =
                estrategiaGuillermo.execute(entradaGuillermo);

        if (respuesta == null) {
            throw new IllegalStateException(
                    "La estrategia devolvio una respuesta nula.");
        }

        if (!respuesta.isSuccess() || respuesta.getData() == null) {
            throw new IllegalStateException(
                    "Error: " + respuesta.getMessage());
        }

        EnergyResult resultado = respuesta.getData();

        return new ResultadoEnergia(
                resultado.getOriginalConsumption(),
                resultado.getFinalConsumption(),
                resultado.getSavingPercentage(),
                resultado.getAction(),
                resultado.getStrategyId()
        );
    }

    private Context convertirContexto(EntradaEnergia entrada) {
        return new Context(
                entrada.getCarga(),
                entrada.getTemperatura(),
                entrada.getTipoDispositivo()
        );
    }
}


package com.mycompany.pe.edu.utp.proyecto_poo;

import pe.edu.utp.proyecto_poo.api.Context;
import pe.edu.utp.proyecto_poo.api.Result;
import pe.edu.utp.proyecto_poo.api.Strategy;
import pe.edu.utp.proyecto_poo.domain.EnergyInput;
import pe.edu.utp.proyecto_poo.domain.EnergyResult;

/** Plugin ECO que conserva el cálculo por tramos del proyecto original. */
public class EstrategiaEcoEnergia implements Estrategia<EntradaEnergia, ResultadoEnergia>,
        Strategy<EnergyInput, EnergyResult> {

    @Override
    public boolean supports(Context context) {
        return context != null && Double.isFinite(context.getLoad())
                && context.getLoad() >= 0 && context.getLoad() <= 100
                && Double.isFinite(context.getTemperature())
                && context.getTemperature() >= -30 && context.getTemperature() <= 120
                && context.getDeviceType() != null && !context.getDeviceType().isBlank();
    }

    @Override
    public Result<EnergyResult> execute(EnergyInput input) {
        long inicio = System.nanoTime();
        if (input == null || !Double.isFinite(input.getConsumption())
                || input.getConsumption() < 0 || !supports(new Context(input.getLoad(),
                        input.getTemperature(), input.getDeviceType()))) {
            return Result.failure("Lectura inválida para ECO.", (System.nanoTime() - inicio) / 1_000_000L);
        }

        var r = ejecutar(new EntradaEnergia(input.getConsumption(), input.getTemperature(),
                input.getLoad(), input.getDeviceType()));
        double ahorro = input.getConsumption() == 0 ? 0 : r.getPorcentajeAhorro();
        return Result.success(new EnergyResult(r.getConsumoOriginal(), r.getConsumoFinal(),
                ahorro, r.getAccion(), id()), "Evaluación ECO completada.",
                (System.nanoTime() - inicio) / 1_000_000L);
    }


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
        
        String accion = "Reducir la potencia estimada según el tramo de carga.";
        
        return new ResultadoEnergia(consumoActual, consumoFinal, porcentajeAhorro * 100, accion, id());
    }
}

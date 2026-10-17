/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.proyecto_poo.plugins.java;

import pe.edu.utp.proyecto_poo.api.Context;
import pe.edu.utp.proyecto_poo.api.Result;
import pe.edu.utp.proyecto_poo.api.Strategy;
import pe.edu.utp.proyecto_poo.domain.EnergyInput;
import pe.edu.utp.proyecto_poo.domain.EnergyResult;

/** Plugin de simulación que da prioridad al rendimiento. */
public final class PerformanceEnergyStrategy
        implements Strategy<EnergyInput, EnergyResult> {

    // Reglas propuestas para demostrar el plugin; no son mediciones de hardware.
    private static final double MIN_LOAD = 70.0;
    private static final double HIGH_LOAD = 90.0;
    private static final double WARM_TEMPERATURE = 75.0;
    private static final double MAX_TEMPERATURE = 85.0;
    private static final double SAVING_RATE = 0.05;

    @Override
    public String id() {
        return "performance";
    }

    @Override
    public boolean supports(Context context) {
        return context != null
                && Double.isFinite(context.getLoad())
                && context.getLoad() >= MIN_LOAD
                && context.getLoad() <= 100.0
                && Double.isFinite(context.getTemperature())
                && context.getTemperature() >= 0.0
                && context.getTemperature() < MAX_TEMPERATURE
                && context.getDeviceType() != null
                && !context.getDeviceType().trim().isEmpty()
                && context.getTimestamp() != null;
    }

    @Override
    public Result<EnergyResult> execute(EnergyInput input) {
        long startedAt = System.nanoTime();

        if (input == null) {
            return Result.failure("Debes ingresar los datos del equipo.", elapsedMs(startedAt));
        }
        if (!Double.isFinite(input.getConsumption()) || input.getConsumption() < 0.0) {
            return Result.failure("El consumo debe ser un número finito y no negativo.",
                    elapsedMs(startedAt));
        }

        Context context = new Context(input.getLoad(), input.getTemperature(),
                input.getDeviceType());
        if (!supports(context)) {
            return Result.failure(
                    "Performance requiere carga entre 70 y 100 %, temperatura de 0 a menos"
                    + " de 85 °C y un tipo de dispositivo válido.", elapsedMs(startedAt));
        }

        double originalConsumption = input.getConsumption();
        double finalConsumption = originalConsumption;
        String action = "Mantener rendimiento; no aplicar reducción de consumo.";

        // Solo se propone una optimización suave cuando hay margen en esta simulación.
        if (input.getLoad() < HIGH_LOAD && input.getTemperature() < WARM_TEMPERATURE) {
            finalConsumption = originalConsumption * (1.0 - SAVING_RATE);
            action = "Simular optimización de procesos en segundo plano (ahorro del 5 %).";
        }

        double savingPercentage = originalConsumption == 0.0 ? 0.0
                : ((originalConsumption - finalConsumption) / originalConsumption) * 100.0;
        EnergyResult data = new EnergyResult(originalConsumption, finalConsumption,
                savingPercentage, action, id());

        return Result.success(data, "Evaluación Performance completada.", elapsedMs(startedAt));
    }

    private long elapsedMs(long startedAt) {
        return (System.nanoTime() - startedAt) / 1_000_000L;
    }
}

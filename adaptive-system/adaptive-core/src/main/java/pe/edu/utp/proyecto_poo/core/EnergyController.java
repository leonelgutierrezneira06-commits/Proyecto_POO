package pe.edu.utp.proyecto_poo.core;

import pe.edu.utp.proyecto_poo.api.Result;
import pe.edu.utp.proyecto_poo.domain.EnergyInput;
import pe.edu.utp.proyecto_poo.domain.EnergyResult;

/** Coordina medición, selección, ejecución y validación de resultados. */
public final class EnergyController {
    public record Evaluation(EnergyResult resultado, String regla, long tiempoMs) {}

    private final Monitor monitor;
    private final StrategySelector selector;

    public EnergyController(Monitor monitor, StrategySelector selector) {
        this.monitor = monitor;
        this.selector = selector;
    }

    public Evaluation evaluar(EnergyInput input) {
        var decision = selector.seleccionar(monitor.medir(input));
        Result<EnergyResult> respuesta = decision.plugin().execute(input);
        if (respuesta == null) throw new IllegalStateException("El plugin no devolvió una respuesta.");
        if (!respuesta.isSuccess()) throw new IllegalStateException(respuesta.getMessage());

        EnergyResult resultado = respuesta.getData();
        double original = input.getConsumption();
        double potenciaFinal = resultado.getFinalConsumption();
        double ahorro = original == 0 ? 0 : (original - potenciaFinal) / original * 100;
        if (!decision.plugin().id().equals(resultado.getStrategyId())
                || original != resultado.getOriginalConsumption()
                || !Double.isFinite(potenciaFinal) || potenciaFinal < 0 || potenciaFinal > original
                || !Double.isFinite(resultado.getSavingPercentage())
                || Math.abs(ahorro - resultado.getSavingPercentage()) > 0.001
                || resultado.getAction() == null || resultado.getAction().isBlank()) {
            throw new IllegalStateException("El plugin devolvió un resultado inconsistente.");
        }
        return new Evaluation(resultado, decision.regla(), respuesta.getExecutionTimeMs());
    }
}

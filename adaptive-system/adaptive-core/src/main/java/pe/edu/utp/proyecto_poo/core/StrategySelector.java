package pe.edu.utp.proyecto_poo.core;

import java.util.Map;
import pe.edu.utp.proyecto_poo.api.Context;
import pe.edu.utp.proyecto_poo.api.Strategy;
import pe.edu.utp.proyecto_poo.domain.EnergyInput;
import pe.edu.utp.proyecto_poo.domain.EnergyResult;

/** Aplica las reglas en orden de prioridad. */
public final class StrategySelector {
    public record Rules(double cargaEco, double cargaPerformance, double temperaturaAlta) {
        public Rules {
            if (!Double.isFinite(cargaEco) || !Double.isFinite(cargaPerformance)
                    || !Double.isFinite(temperaturaAlta) || cargaEco <= 0
                    || cargaEco > cargaPerformance || cargaPerformance < 70
                    || cargaPerformance > 100 || temperaturaAlta <= 0 || temperaturaAlta > 85) {
                throw new IllegalArgumentException("Umbrales inválidos en config.json.");
            }
        }
    }

    public record Decision(Strategy<EnergyInput, EnergyResult> plugin, String regla) {}

    private final Rules rules;
    private final Map<String, Strategy<EnergyInput, EnergyResult>> plugins;

    public StrategySelector(Rules rules, Map<String, Strategy<EnergyInput, EnergyResult>> plugins) {
        this.rules = rules;
        this.plugins = Map.copyOf(plugins);
        for (String id : new String[]{"ECO_ENERGIA", "performance", "adaptive-scala"}) {
            if (!plugins.containsKey(id)) {
                throw new IllegalArgumentException("Falta el plugin " + id + ".");
            }
        }
    }

    public Decision seleccionar(Context contexto) {
        if (contexto.getTemperature() >= rules.temperaturaAlta()) {
            return decision("adaptive-scala", "Prioridad térmica: se alcanzó el umbral de temperatura.", contexto);
        }
        if (contexto.getLoad() >= rules.cargaPerformance()
                && plugins.get("performance").supports(contexto)) {
            return decision("performance", "Carga alta con temperatura apta para rendimiento.", contexto);
        }
        if (contexto.getLoad() < rules.cargaEco()) {
            return decision("ECO_ENERGIA", "Carga baja: se prioriza el ahorro de potencia.", contexto);
        }
        return decision("adaptive-scala", "Ajuste gradual según carga y temperatura.", contexto);
    }

    private Decision decision(String id, String regla, Context contexto) {
        var plugin = plugins.get(id);
        if (!plugin.supports(contexto)) {
            throw new IllegalStateException("El plugin " + id + " rechazó la lectura seleccionada.");
        }
        return new Decision(plugin, regla);
    }
}

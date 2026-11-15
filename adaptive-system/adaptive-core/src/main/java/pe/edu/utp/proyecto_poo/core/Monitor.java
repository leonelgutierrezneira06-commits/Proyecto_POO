package pe.edu.utp.proyecto_poo.core;

import pe.edu.utp.proyecto_poo.api.Context;
import pe.edu.utp.proyecto_poo.domain.EnergyInput;

/** Valida las lecturas del simulador y las convierte en el contexto de selección. */
public final class Monitor {
    public Context medir(EnergyInput input) {
        if (input == null) throw new IllegalArgumentException("Ingresa las métricas.");
        validar(input.getConsumption(), 0, 1_000_000, "Potencia (W)");
        validar(input.getLoad(), 0, 100, "Carga (%)");
        validar(input.getTemperature(), -30, 120, "Temperatura (°C)");
        if (input.getDeviceType() == null || input.getDeviceType().isBlank()
                || input.getDeviceType().length() > 60) {
            throw new IllegalArgumentException("Ingresa un tipo de dispositivo de hasta 60 caracteres.");
        }
        return new Context(input.getLoad(), input.getTemperature(), input.getDeviceType());
    }

    private static void validar(double valor, double min, double max, String campo) {
        if (!Double.isFinite(valor) || valor < min || valor > max) {
            throw new IllegalArgumentException(campo + " debe estar entre " + min + " y " + max + ".");
        }
    }
}

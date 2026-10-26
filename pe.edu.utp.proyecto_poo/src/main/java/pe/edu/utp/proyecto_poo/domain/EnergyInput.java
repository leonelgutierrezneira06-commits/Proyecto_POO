/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.proyecto_poo.domain;

/** REFERENCIA para coordinar con Ronald: él es responsable de este archivo. */
public final class EnergyInput {
    private final double consumption; // Lectura en W en esta simulación.
    private final double temperature; // °C.
    private final double load;        // Porcentaje de 0 a 100.
    private final String deviceType;

    public EnergyInput(double consumption, double temperature, double load, String deviceType) {
        this.consumption = consumption;
        this.temperature = temperature;
        this.load = load;
        this.deviceType = deviceType;
    }

    public double getConsumption() { return consumption; }
    public double getTemperature() { return temperature; }
    public double getLoad() { return load; }
    public String getDeviceType() { return deviceType; }
}


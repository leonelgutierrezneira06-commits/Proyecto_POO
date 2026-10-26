/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.proyecto_poo.api;

import java.time.LocalDateTime;

/** Datos que una estrategia consulta para saber si puede utilizarse. */
public final class Context {
    private final double load;
    private final double temperature;
    private final String deviceType;
    private final LocalDateTime timestamp;

    public Context(double load, double temperature, String deviceType) {
        this(load, temperature, deviceType, LocalDateTime.now());
    }

    public Context(double load, double temperature, String deviceType,
                   LocalDateTime timestamp) {
        this.load = load;
        this.temperature = temperature;
        this.deviceType = deviceType;
        this.timestamp = timestamp;
    }

    public double getLoad() { return load; }
    public double getTemperature() { return temperature; }
    public String getDeviceType() { return deviceType; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

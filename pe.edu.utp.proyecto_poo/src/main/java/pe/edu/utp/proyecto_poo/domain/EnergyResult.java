/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.proyecto_poo.domain;

/** Resultado de la simulación. Las lecturas de consumo se expresan en W. */
public final class EnergyResult {
    private final double originalConsumption;
    private final double finalConsumption;
    private final double savingPercentage;
    private final String action;
    private final String strategyId;

    public EnergyResult(double originalConsumption, double finalConsumption,
                        double savingPercentage, String action, String strategyId) {
        this.originalConsumption = originalConsumption;
        this.finalConsumption = finalConsumption;
        this.savingPercentage = savingPercentage;
        this.action = action;
        this.strategyId = strategyId;
    }

    public double getOriginalConsumption() { return originalConsumption; }
    public double getFinalConsumption() { return finalConsumption; }
    public double getSavingPercentage() { return savingPercentage; }
    public String getAction() { return action; }
    public String getStrategyId() { return strategyId; }
}

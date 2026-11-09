package com.mycompany.pe.edu.utp.proyecto_poo;

public class ScenarioRunner {

    public void ejecutarEscenario1(Object plugin) {
        System.out.println("\n=== INICIANDO ESCENARIO 1: VALIDACIÓN DE CARGA DINÁMICA ===");
        if (plugin != null) {
            System.out.println("-> Plugin instanciado y verificado en tiempo de ejecución.");
            System.out.println("-> Tipo de clase instanciada: " + plugin.getClass().getCanonicalName());
        } else {
            System.out.println("-> No se pudo ejecutar el escenario: Plugin nulo.");
        }
        System.out.println("=== FIN DEL ESCENARIO 1 ===\n");
    }
}

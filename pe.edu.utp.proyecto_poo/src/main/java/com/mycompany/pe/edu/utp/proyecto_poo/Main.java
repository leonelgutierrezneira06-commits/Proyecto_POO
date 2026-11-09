package com.mycompany.pe.edu.utp.proyecto_poo;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" Sistema Autoadaptativo de Control Energético ");
        System.out.println("=================================================");

        String rutaEjemploJar = "plugins/estrategia-eco.jar";
        String clasePluginEjemplo = "com.mycompany.pe.edu.utp.proyecto_poo.EstrategiaEcoEnergia";

        Object pluginCargado = PluginLoader.cargarInstanciaPlugin(rutaEjemploJar, clasePluginEjemplo);

        ScenarioRunner runner = new ScenarioRunner();
        runner.ejecutarEscenario1(pluginCargado);
    }
}

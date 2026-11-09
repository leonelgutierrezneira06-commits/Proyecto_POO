package com.mycompany.pe.edu.utp.proyecto_poo;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    public static Object cargarInstanciaPlugin(String rutaJar, String nombreClaseCompleto) {
        try {
            File archivoJar = new File(rutaJar);
            if (!archivoJar.exists()) {
                System.err.println("[PluginLoader] Error: No se encontró el archivo JAR en: " + rutaJar);
                return null;
            }

            URL[] urls = { archivoJar.toURI().toURL() };
            try (URLClassLoader classLoader = new URLClassLoader(urls, PluginLoader.class.getClassLoader())) {
                Class<?> claseCargada = Class.forName(nombreClaseCompleto, true, classLoader);
                Constructor<?> constructor = claseCargada.getDeclaredConstructor();
                
                System.out.println("[PluginLoader] Clase cargada exitosamente mediante Reflection: " + claseCargada.getName());
                return constructor.newInstance();
            }
        } catch (Exception e) {
            System.err.println("[PluginLoader] Error al cargar la clase vía Reflection: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

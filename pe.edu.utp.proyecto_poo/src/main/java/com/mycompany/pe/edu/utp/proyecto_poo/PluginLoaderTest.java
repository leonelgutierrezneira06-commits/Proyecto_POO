package com.mycompany.pe.edu.utp.proyecto_poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PluginLoaderTest {

    @Test
    public void testCargarPluginArchivoNoExistente() {
        // Verifica que devuelva null de forma segura si el JAR no existe
        Object resultado = PluginLoader.cargarInstanciaPlugin("archivo_falso.jar", "com.mycompany.pe.edu.utp.proyecto_poo.EstrategiaEcoEnergia");
        assertNull(resultado, "El resultado debe ser null si el archivo no existe");
    }
}

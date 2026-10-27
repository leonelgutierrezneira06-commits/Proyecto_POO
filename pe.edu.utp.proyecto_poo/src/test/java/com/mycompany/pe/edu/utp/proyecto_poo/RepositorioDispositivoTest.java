
package com.mycompany.pe.edu.utp.proyecto_poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepositorioDispositivoTest {

    @Test
    public void probarGuardarYRecuperarDispositivo()
            throws SQLException {

        ConexionSQLite.crearTablasIniciales();

        String nombre = "PC_PRUEBA_" + UUID.randomUUID();

        DISPOSITIVO dispositivo = new DISPOSITIVO();

        dispositivo.setNombre(nombre);
        dispositivo.setTipo("PC Escritorio");
        dispositivo.setConsumoVatios(750.0);

        RepositorioDispositivo repositorio =
                new RepositorioDispositivo();

        try {

            repositorio.guardar(dispositivo);

            List<DISPOSITIVO> lista =
                    repositorio.obtenerTodos();

            DISPOSITIVO recuperado = lista.stream()
                    .filter(d -> nombre.equals(d.getNombre()))
                    .findFirst()
                    .orElse(null);

            assertNotNull(recuperado,
                    "No se encontro el dispositivo en SQLite");

            assertEquals("PC Escritorio",
                    recuperado.getTipo());

            assertEquals(750.0,
                    recuperado.getConsumoVatios(), 0.001);

            System.out.println("PRUEBA EXITOSA");
            System.out.println("Dispositivo guardado y recuperado");
            System.out.println("Nombre: " + recuperado.getNombre());
            System.out.println("Tipo: " + recuperado.getTipo());
            System.out.println("Consumo: "
                    + recuperado.getConsumoVatios() + " W");

        } finally {

            String sql = "DELETE FROM dispositivo WHERE nombre = ?";

            try (Connection con = ConexionSQLite.conectar();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, nombre);
                ps.executeUpdate();
            }
        }
    }
}

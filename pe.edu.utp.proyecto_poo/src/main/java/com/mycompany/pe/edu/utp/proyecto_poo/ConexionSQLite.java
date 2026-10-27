package com.mycompany.pe.edu.utp.proyecto_poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSQLite {
    private static final String URL = "jdbc:sqlite:control_energetico.db";
    
    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a SQLite.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return con;
    }

    public static void crearTablasIniciales() {
        String tablaDispositivo = "CREATE TABLE IF NOT EXISTS dispositivo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre TEXT NOT NULL, "
                + "tipo TEXT, "
                + "consumo_vatios REAL"
                + ");";

        try (Connection con = conectar(); Statement stmt = con.createStatement()) {
            stmt.execute(tablaDispositivo);
            System.out.println("Tabla 'dispositivo' verificada/creada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}
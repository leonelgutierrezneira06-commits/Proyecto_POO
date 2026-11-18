package com.mycompany.pe.edu.utp.proyecto_poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDispositivo {

    public void guardar(DISPOSITIVO dispositivo) {
        String sql = "INSERT INTO dispositivo(nombre, tipo, consumo_vatios) VALUES(?,?,?)";
        
        try (Connection con = ConexionSQLite.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, dispositivo.getNombre());
            pstmt.setString(2, dispositivo.getTipo());
            pstmt.setDouble(3, dispositivo.getConsumoVatios());
            pstmt.executeUpdate();
            System.out.println("Dispositivo guardado con éxito en la base de datos.");
            
        } catch (SQLException e) {
            System.out.println("Error al guardar dispositivo: " + e.getMessage());
        }
    }

    public List<DISPOSITIVO> obtenerTodos() {
        List<DISPOSITIVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM dispositivo";
        
        try (Connection con = ConexionSQLite.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                DISPOSITIVO d = new DISPOSITIVO();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setTipo(rs.getString("tipo"));
                d.setConsumoVatios(rs.getDouble("consumo_vatios"));
                lista.add(d);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener dispositivos: " + e.getMessage());
        }
        return lista;
    }
}
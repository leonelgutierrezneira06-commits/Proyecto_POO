package com.mycompany.pe.edu.utp.proyecto_poo;

public interface Estrategia<E, S> {

    String id();

    boolean soporta(Object contexto);

    S ejecutar(E entrada);
}
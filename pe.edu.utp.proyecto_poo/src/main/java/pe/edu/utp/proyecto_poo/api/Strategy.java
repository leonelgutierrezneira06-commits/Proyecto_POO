/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.proyecto_poo.api;

/** REFERENCIA para coordinar con Ronald: él es responsable de este archivo.
 * @param <I>
 * @param <O> */
public interface Strategy<I, O> {
    String id();
    boolean supports(Context context);
    Result<O> execute(I input);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.proyecto_poo.api;

import java.util.Objects;

/** Respuesta común: indica éxito o error y contiene los datos del resultado. */
public final class Result<T> {
    private final boolean success;
    private final T data;
    private final String message;
    private final long executionTimeMs;

    private Result(boolean success, T data, String message, long executionTimeMs) {
        if (executionTimeMs < 0) {
            throw new IllegalArgumentException("El tiempo no puede ser negativo.");
        }
        this.success = success;
        this.data = data;
        this.message = Objects.requireNonNull(message, "El mensaje es obligatorio.");
        this.executionTimeMs = executionTimeMs;
    }

    public static <T> Result<T> success(T data, String message, long executionTimeMs) {
        return new Result<>(true, Objects.requireNonNull(data), message, executionTimeMs);
    }

    public static <T> Result<T> failure(String message, long executionTimeMs) {
        return new Result<>(false, null, message, executionTimeMs);
    }

    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public String getMessage() { return message; }
    public long getExecutionTimeMs() { return executionTimeMs; }
}


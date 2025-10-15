package com.victor.quotes.quotes_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta estructurada de la API")
public class ApiResponse<T> {

    @Schema(description = "Mensaje descriptivo de la operación", example = "✅ Citas obtenidas exitosamente")
    private String message;

    @Schema(description = "Datos de la respuesta")
    private T data;

    // Constructor vacío
    public ApiResponse() {
    }

    // Constructor con parámetros
    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // Getters y setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
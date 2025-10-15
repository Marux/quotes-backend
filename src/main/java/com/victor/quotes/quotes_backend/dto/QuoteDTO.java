package com.victor.quotes.quotes_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "DTO para crear o mostrar una cita")
public class QuoteDTO {

    @Schema(
        description = "ID único de la cita",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    private UUID id;

    @Schema(
        description = "La frase o cita",
        example = "La vida es lo que pasa mientras estás ocupado haciendo otros planes."
    )
    private String frase;

    @Schema(
        description = "El autor de la cita",
        example = "John Lennon"
    )
    private String autor;

    @Schema(
        description = "HTML o formato enriquecido de la cita",
        example = "<blockquote>La vida es lo que pasa mientras estás ocupado...</blockquote>"
    )
    private String html;

    // Constructor vacío obligatorio para frameworks
    public QuoteDTO() {}

    // Constructor para crear nuevas citas (sin ID)
    public QuoteDTO(String frase, String autor, String html) {
        this.frase = frase;
        this.autor = autor;
        this.html = html;
    }

    // Constructor completo (con ID)
    public QuoteDTO(UUID id, String frase, String autor, String html) {
        this.id = id;
        this.frase = frase;
        this.autor = autor;
        this.html = html;
    }

    // Getters y setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
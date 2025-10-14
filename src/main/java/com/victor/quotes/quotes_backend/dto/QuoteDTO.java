package com.victor.quotes.quotes_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para crear o mostrar una cita")
public class QuoteDTO {

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

    // Constructor completo
    public QuoteDTO(String frase, String autor, String html) {
        this.frase = frase;
        this.autor = autor;
        this.html = html;
    }

    // Getters y setters
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

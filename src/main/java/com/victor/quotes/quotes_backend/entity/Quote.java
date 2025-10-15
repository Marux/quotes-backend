package com.victor.quotes.quotes_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Entity
public class Quote {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "La frase no puede estar vacía")
    @Size(max = 255, message = "La frase no puede tener más de 255 caracteres")
    private String q; // la frase
    @NotBlank(message = "El autor no puede estar vacío")
    @Size(max = 255, message = "El autor no puede tener más de 255 caracteres")
    private String a; // autor
    @NotBlank(message = "El HTML no puede estar vacío")
    private String h; // HTML o formato enriquecido

    // Constructor vacío obligatorio para JPA
    public Quote() {}

    // Constructor con todos los campos (excepto id)
    public Quote(String q, String a, String h) {
        this.q = q;
        this.a = a;
        this.h = h;
    }

    // Getters y setters
    public UUID getId() {
        return id;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }
}
package com.victor.quotes.quotes_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class Quote {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String q; // la frase
    private String a; // autor
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

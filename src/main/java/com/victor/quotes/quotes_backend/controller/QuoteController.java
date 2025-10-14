package com.victor.quotes.quotes_backend.controller;

import com.victor.quotes.quotes_backend.dto.QuoteDTO;
import com.victor.quotes.quotes_backend.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quotes")
@Tag(name = "Citas", description = "Operaciones sobre las citas")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las citas")
    public List<QuoteDTO> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cita por ID")
    public ResponseEntity<QuoteDTO> getQuoteById(@PathVariable UUID id) {
        QuoteDTO dto = quoteService.getQuoteById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cita")
    public ResponseEntity<QuoteDTO> createQuote(@RequestBody QuoteDTO dto) {
        return ResponseEntity.ok(quoteService.createQuote(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cita existente")
    public ResponseEntity<QuoteDTO> updateQuote(@PathVariable UUID id, @RequestBody QuoteDTO dto) {
        QuoteDTO updated = quoteService.updateQuote(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cita por ID")
    public ResponseEntity<Void> deleteQuote(@PathVariable UUID id) {
        return quoteService.deleteQuote(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

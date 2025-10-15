package com.victor.quotes.quotes_backend.controller;

import com.victor.quotes.quotes_backend.dto.QuoteDTO;
import com.victor.quotes.quotes_backend.dto.ApiResponse;
import com.victor.quotes.quotes_backend.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
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
    public ApiResponse<List<QuoteDTO>> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cita por ID")
    public ResponseEntity<ApiResponse<QuoteDTO>> getQuoteById(@PathVariable UUID id) {
        ApiResponse<QuoteDTO> response = quoteService.getQuoteById(id);

        if (response.getData() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cita")
    public ResponseEntity<ApiResponse<QuoteDTO>> createQuote(@Valid @RequestBody QuoteDTO dto) {
        ApiResponse<QuoteDTO> response = quoteService.createQuote(dto);

        if (response.getMessage().contains("✅")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cita existente")
    public ResponseEntity<ApiResponse<QuoteDTO>> updateQuote(@PathVariable UUID id, @Valid @RequestBody QuoteDTO dto) {
        ApiResponse<QuoteDTO> response = quoteService.updateQuote(id, dto);

        if (response.getMessage().contains("✅")) {
            return ResponseEntity.ok(response);
        } else if (response.getMessage().contains("no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cita por ID")
    public ResponseEntity<ApiResponse<Void>> deleteQuote(@PathVariable UUID id) {
        ApiResponse<Void> response = quoteService.deleteQuote(id);

        if (response.getMessage().contains("✅")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}

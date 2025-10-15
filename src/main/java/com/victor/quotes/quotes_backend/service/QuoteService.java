package com.victor.quotes.quotes_backend.service;

import com.victor.quotes.quotes_backend.dto.QuoteDTO;
import com.victor.quotes.quotes_backend.dto.ApiResponse;
import com.victor.quotes.quotes_backend.entity.Quote;
import com.victor.quotes.quotes_backend.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    // 🔹 Obtener todas las citas
    public ApiResponse<List<QuoteDTO>> getAllQuotes() {
        try {
            List<QuoteDTO> quotes = quoteRepository.findAll()
                    .stream()
                    .map(q -> new QuoteDTO(q.getId(), q.getQ(), q.getA(), q.getH()))
                    .collect(Collectors.toList());

            return new ApiResponse<>("✅ Citas obtenidas exitosamente", quotes);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving quotes", e);
        }
    }

    // 🔹 Obtener cita por ID
    public ApiResponse<QuoteDTO> getQuoteById(UUID id) {
        try {
            QuoteDTO quoteDTO = quoteRepository.findById(id)
                    .map(q -> new QuoteDTO(q.getId(), q.getQ(), q.getA(), q.getH()))
                    .orElse(null);

            if (quoteDTO != null) {
                return new ApiResponse<>("✅ Cita obtenida exitosamente", quoteDTO);
            } else {
                return new ApiResponse<>("❌ Cita no encontrada", null);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving quote by ID", e);
        }
    }

    // 🔹 Crear nueva cita
    public ApiResponse<QuoteDTO> createQuote(QuoteDTO dto) {
        try {
            // Validación manual adicional (opcional)
            if (dto.getFrase() == null || dto.getFrase().trim().isEmpty()) {
                return new ApiResponse<>("❌ La frase no puede estar vacía", null);
            }
            if (dto.getAutor() == null || dto.getAutor().trim().isEmpty()) {
                return new ApiResponse<>("❌ El autor no puede estar vacío", null);
            }
            if (dto.getHtml() == null || dto.getHtml().trim().isEmpty()) {
                return new ApiResponse<>("❌ El HTML no puede estar vacío", null);
            }

            Quote quote = new Quote(dto.getFrase(), dto.getAutor(), dto.getHtml());
            Quote saved = quoteRepository.save(quote);
            QuoteDTO savedDTO = new QuoteDTO(saved.getId(), saved.getQ(), saved.getA(), saved.getH());

            return new ApiResponse<>("✅ Cita creada exitosamente", savedDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error creating quote", e);
        }
    }

    // 🔹 Actualizar cita existente
    public ApiResponse<QuoteDTO> updateQuote(UUID id, QuoteDTO dto) {
        try {
            // Validaciones básicas
            if (dto.getFrase() == null || dto.getFrase().trim().isEmpty()) {
                return new ApiResponse<>("❌ La frase no puede estar vacía", null);
            }
            if (dto.getAutor() == null || dto.getAutor().trim().isEmpty()) {
                return new ApiResponse<>("❌ El autor no puede estar vacío", null);
            }
            if (dto.getHtml() == null || dto.getHtml().trim().isEmpty()) {
                return new ApiResponse<>("❌ El HTML no puede estar vacío", null);
            }

            Optional<Quote> existingQuote = quoteRepository.findById(id);

            if (existingQuote.isPresent()) {
                Quote quote = existingQuote.get();
                quote.setQ(dto.getFrase().trim());
                quote.setA(dto.getAutor().trim());
                quote.setH(dto.getHtml().trim());

                Quote updated = quoteRepository.save(quote);
                QuoteDTO updatedDTO = new QuoteDTO(updated.getId(), updated.getQ(), updated.getA(), updated.getH());

                return new ApiResponse<>("✅ Cita actualizada exitosamente", updatedDTO);
            } else {
                return new ApiResponse<>("❌ Cita no encontrada", null);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating quote", e);
        }
    }

    // 🔹 Eliminar cita
    public ApiResponse<Void> deleteQuote(UUID id) {
        try {
            if (quoteRepository.existsById(id)) {
                quoteRepository.deleteById(id);
                return new ApiResponse<>("✅ Cita eliminada exitosamente", null);
            } else {
                return new ApiResponse<>("❌ Cita no encontrada", null);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting quote", e);
        }
    }
}

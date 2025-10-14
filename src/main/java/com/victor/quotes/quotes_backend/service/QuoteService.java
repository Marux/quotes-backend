package com.victor.quotes.quotes_backend.service;

import com.victor.quotes.quotes_backend.dto.QuoteDTO;
import com.victor.quotes.quotes_backend.entity.Quote;
import com.victor.quotes.quotes_backend.repository.QuoteRepository;
import org.springframework.stereotype.Service;

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
    public List<QuoteDTO> getAllQuotes() {
        try {
            return quoteRepository.findAll()
                    .stream()
                    .map(q -> new QuoteDTO(q.getQ(), q.getA(), q.getH()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving quotes", e);
        }
    }

    // 🔹 Obtener cita por ID
    public QuoteDTO getQuoteById(UUID id) {
        try {
            return quoteRepository.findById(id)
                    .map(q -> new QuoteDTO(q.getQ(), q.getA(), q.getH()))
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving quote by ID", e);
        }
    }

    // 🔹 Crear nueva cita
    public QuoteDTO createQuote(QuoteDTO dto) {
        try {
            Quote quote = new Quote(dto.getFrase(), dto.getAutor(), dto.getHtml());
            Quote saved = quoteRepository.save(quote);
            return new QuoteDTO(saved.getQ(), saved.getA(), saved.getH());
        } catch (Exception e) {
            throw new RuntimeException("Error creating quote", e);
        }
    }

    // 🔹 Actualizar cita existente
    public QuoteDTO updateQuote(UUID id, QuoteDTO dto) {
        try {
            return quoteRepository.findById(id)
                    .map(quote -> {
                        quote.setQ(dto.getFrase());
                        quote.setA(dto.getAutor());
                        quote.setH(dto.getHtml());
                        Quote updated = quoteRepository.save(quote);
                        return new QuoteDTO(updated.getQ(), updated.getA(), updated.getH());
                    })
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error updating quote", e);
        }
    }

    // 🔹 Eliminar cita
    public boolean deleteQuote(UUID id) {
        try {
            if (quoteRepository.existsById(id)) {
                quoteRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting quote", e);
        }
    }
}

package com.gerhotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerhotel.entities.Quarto;
import com.gerhotel.repository.QuartoRepository;

@Service
public class QuartoService {

	private final QuartoRepository quartoRepository;

	@Autowired
	public QuartoService(QuartoRepository quartoRepository) {
		this.quartoRepository = quartoRepository;
	}

	public List<Quarto> getAllQuartos() {
		return quartoRepository.findAll();
	}

	public Quarto getQuartoById(Long id) {
		Optional<Quarto> quarto = quartoRepository.findById(id);
		return quarto.orElse(null);
	}

	public Quarto salvarQuarto(Quarto quarto) {
		return quartoRepository.save(quarto);
	}

	public Quarto updatedQuarto(Long id, Quarto updatedQuarto) {
		Optional<Quarto> existingQuarto = quartoRepository.findById(id);
		if (existingQuarto.isPresent()) {
			updatedQuarto.setId(id);
			return quartoRepository.save(updatedQuarto);
		}
		return null;
	}

	public boolean deleteQuarto(Long id) {
		Optional<Quarto> existingQuarto = quartoRepository.findById(id);
		if (existingQuarto.isPresent()) {
			quartoRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

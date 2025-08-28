package com.gerhotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerhotel.entities.Reserva;
import com.gerhotel.repository.ReservaRepository;

@Service
public class ReservaService {
	
	private final ReservaRepository reservaRepository;
	
	@Autowired
	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}
	
	public List<Reserva> getAllReservas() {
		return reservaRepository.findAll();
	}
	
	public Reserva getReservaById(Long id) {
		Optional<Reserva> reserva = reservaRepository.findById(id);
		return reserva.orElse(null);
	}
	
	public Reserva salvarReserva(Reserva reserva) {
		return reservaRepository.save(reserva);
	}
	
	public Reserva updateReserva(Long id, Reserva updateReserva) {
		Optional<Reserva> existingReserva = reservaRepository.findById(id);
		if (existingReserva.isPresent()) {
			updateReserva.setId(id);
			return reservaRepository.save(updateReserva);
		}
		return null;
	}
	
	public boolean deleteReserva(Long id) {
		Optional<Reserva> existingReserva = reservaRepository.findById(id);
		if (existingReserva.isPresent()) {
			reservaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}

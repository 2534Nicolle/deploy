package com.gerhotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerhotel.entities.Reserva;
import com.gerhotel.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

	private final ReservaService reservaService;

	@Autowired
	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reserva> buscaReservaControlId(@PathVariable Long id) {
		Reserva reserva = reservaService.getReservaById(id);
		if (reserva != null) {
			return ResponseEntity.ok(reserva);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping
	public ResponseEntity<List<Reserva>> buscaTodosReservaControl() {
		List<Reserva> Reservas = reservaService.getAllReservas();
		return ResponseEntity.ok(Reservas);
	}

	@PostMapping
	public ResponseEntity<Reserva> salvaReservasControl(@RequestBody @Valid Reserva reserva) {
		Reserva salvaReserva = reservaService.salvarReserva(reserva);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaReserva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reserva> alteraReservaControl(@PathVariable Long id, @RequestBody @Valid Reserva reserva) {
		Reserva alteraReserva = reservaService.updateReserva(id, reserva);
		if (alteraReserva != null) {
			return ResponseEntity.ok(reserva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Reserva> apagaReservaControl(@PathVariable Long id) {
		boolean apagar = reservaService.deleteReserva(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

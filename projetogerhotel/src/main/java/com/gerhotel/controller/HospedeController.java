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

import com.gerhotel.entities.Hospede;
import com.gerhotel.service.HospedeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospede")
public class HospedeController {
	
	private final HospedeService hospedeService;
	
	@Autowired
	public HospedeController(HospedeService hospedeService) {
		this.hospedeService = hospedeService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hospede> buscaHospedeControlId(@PathVariable Long id) {
		Hospede hospede = hospedeService.getHospedeById(id);
		if (hospede != null) {
			return ResponseEntity.ok(hospede);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	@GetMapping
	public ResponseEntity<List<Hospede>> buscaTodosHospedesControl() {
		List<Hospede> Hospede = hospedeService.getAllHospedes();
		return ResponseEntity.ok(Hospede);
	}
	
	@PostMapping
	public ResponseEntity<Hospede> salvaHospedesControl(@RequestBody @Valid Hospede hospede) {
		Hospede salvaHospede = hospedeService.salvarHospede(hospede);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaHospede);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hospede> alteraHospedeControl(@PathVariable Long id, @RequestBody @Valid Hospede hospede) {
		Hospede alteraHospede = hospedeService.updateHospede(id, hospede);
		if (alteraHospede != null) {
			return ResponseEntity.ok(hospede);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Hospede> apagaHospedeControl(@PathVariable Long id) {
		boolean apagar = hospedeService.deleteHospede(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}



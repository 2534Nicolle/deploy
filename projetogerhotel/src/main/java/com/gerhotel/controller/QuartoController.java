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

import com.gerhotel.entities.Quarto;
import com.gerhotel.service.QuartoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quarto")
public class QuartoController {
	
	private final QuartoService quartoService;
	
	@Autowired
	public QuartoController(QuartoService quartoService) {
		this.quartoService = quartoService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Quarto> buscaQuartoControlId(@PathVariable Long id) {
		Quarto quarto = quartoService.getQuartoById(id);
		if (quarto != null) {
			return ResponseEntity.ok(quarto);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	@GetMapping
	public ResponseEntity<List<Quarto>> buscaTodosQuartosControl() {
		List<Quarto> Quartos = quartoService.getAllQuartos();
		return ResponseEntity.ok(Quartos);
	}
	
	@PostMapping
	public ResponseEntity<Quarto> salvaQuartosControl(@RequestBody @Valid Quarto quarto) {
		Quarto salvaQuarto = quartoService.salvarQuarto(quarto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaQuarto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Quarto> alteraQuartoControl(@PathVariable Long id, @RequestBody @Valid Quarto quarto) {
		Quarto alteraQuarto = quartoService.updatedQuarto(id, quarto);
		if (alteraQuarto != null) {
			return ResponseEntity.ok(quarto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Quarto> apagaQuartoControl(@PathVariable Long id) {
		boolean apagar = quartoService.deleteQuarto(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

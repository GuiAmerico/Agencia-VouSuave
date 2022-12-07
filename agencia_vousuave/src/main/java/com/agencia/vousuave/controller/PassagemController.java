package com.agencia.vousuave.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

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

import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.service.PassagemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/passagens")
@RequiredArgsConstructor
public class PassagemController {

	
	private final PassagemService service;

	@GetMapping
	public List<PassagemDTO> findAll() {
		return service.findAll();

	}

	@PostMapping
	public ResponseEntity<PassagemDTO> save(@RequestBody @Valid PassagemDTO passagemDTO) throws ParseException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(passagemDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PassagemDTO> update(@PathVariable Integer id, @RequestBody @Valid PassagemDTO passagemDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(passagemDTO, id));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Passagem deletada com sucesso!");
	}
}

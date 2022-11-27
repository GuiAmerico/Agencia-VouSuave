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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.service.PacoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pacotes")
@RequiredArgsConstructor
public class PacoteController {

	private final PacoteService service;
	

	@GetMapping
	public ResponseEntity<List<Pacote>> getAllPacotes() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());

	}

	@PostMapping
	public ResponseEntity<Pacote> save(@RequestBody @Valid Pacote pacote) throws ParseException {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pacote));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)	
	public ResponseEntity<Pacote> update( @PathVariable Integer id,@RequestBody @Valid Pacote pacote){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(pacote, id));
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pacote deletado com sucesso!");
	}
}

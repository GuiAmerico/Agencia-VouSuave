package com.agencia.vousuave.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.service.PacoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pacotes")
@RequiredArgsConstructor
public class PacoteController {

	private final PacoteService service;
	

	@GetMapping
	public ResponseEntity<Page<PacoteDTO>> getAllPacotes(@PageableDefault(size = 6, page = 0) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));

	}

	@PostMapping
	public ResponseEntity<PacoteDTO> save(@RequestBody @Valid PacoteDTO pacoteDTO) throws ParseException {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pacoteDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PacoteDTO> update( @PathVariable Integer id,@RequestBody @Valid PacoteDTO pacoteDTO){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(pacoteDTO, id));
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pacote deletado com sucesso!");
	}
}

package com.agencia.vousuave.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
	@ResponseStatus(HttpStatus.OK)
	public List<Pacote> getAllPacotes() {
		return service.findAll();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pacote save(@RequestBody @Valid Pacote pacote) throws ParseException {
		
		return service.save(pacote);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)	
	public Pacote update( @PathVariable Integer id,@RequestBody @Valid Pacote pacote){
		return service.update(pacote, id);
	
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void delete(@PathVariable Integer id){
		service.deleteById(id);
	}
}

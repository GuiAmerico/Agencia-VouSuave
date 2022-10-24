package com.agencia.vousuave.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.repository.PacoteRepository;

@RestController
@RequestMapping("/api/pacotes")
@CrossOrigin(origins = "*")
public class PacoteController {

	@Autowired
	private PacoteRepository repository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Pacote> getAllPacotes() {
		return repository.findAll();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pacote save(@RequestBody @Valid Pacote pacote) throws ParseException {
		return repository.save(pacote);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public Pacote update( @PathVariable Integer id,@RequestBody @Valid Pacote pacote){
		return repository.findById(id).map(p -> {
			pacote.setId(p.getId());
			repository.save(pacote);
			return p;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado"));
	
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void delete(@PathVariable Integer id){
		repository.deleteById(id);
	}
}

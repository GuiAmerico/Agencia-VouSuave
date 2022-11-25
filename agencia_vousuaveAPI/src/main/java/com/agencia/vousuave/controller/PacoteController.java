package com.agencia.vousuave.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/PacoteController.java
import org.springframework.http.HttpStatus;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/PacoteController.java
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/PacoteController.java

import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.service.PacoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pacotes")
@RequiredArgsConstructor
public class PacoteController {

	private final PacoteService service;
	
=======
import org.springframework.web.server.ResponseStatusException;

import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.repository.PacoteRepository;

@RestController
@RequestMapping("/api/pacotes")
@CrossOrigin(origins = "*")
public class PacoteController {

	@Autowired
	private PacoteRepository repository;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/PacoteController.java

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Pacote> getAllPacotes() {
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/PacoteController.java
		return service.findAll();
=======
		return repository.findAll();
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/PacoteController.java

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pacote save(@RequestBody @Valid Pacote pacote) throws ParseException {
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/PacoteController.java
		
		return service.save(pacote);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)	
	public Pacote update( @PathVariable Integer id,@RequestBody @Valid Pacote pacote){
		return service.update(pacote, id);
=======
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
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/PacoteController.java
	
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void delete(@PathVariable Integer id){
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/PacoteController.java
		service.deleteById(id);
=======
		repository.deleteById(id);
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/PacoteController.java
	}
}

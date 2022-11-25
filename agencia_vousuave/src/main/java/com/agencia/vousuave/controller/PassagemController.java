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

import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.service.PassagemService;

@RestController
@RequestMapping("/api/passagens")
public class PassagemController {

	@Autowired
	private PassagemService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Passagem> getAllPassagens() {
		return service.findAll();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Passagem save(@RequestBody @Valid Passagem passagem) throws ParseException {
		return service.save(passagem);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Passagem update(@PathVariable Integer id, @RequestBody @Valid Passagem passagem) {
		return service.update(passagem, id);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}

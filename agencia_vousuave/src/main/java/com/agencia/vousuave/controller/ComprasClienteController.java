package com.agencia.vousuave.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.vousuave.entity.ComprasCliente;
import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.service.ComprasClienteService;
import com.agencia.vousuave.service.PacoteService;
import com.agencia.vousuave.service.PassagemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class ComprasClienteController {

	private final ComprasClienteService service;
	private final PacoteService pacoteService;
	private final PassagemService passagemService;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ComprasCliente> getAllCompras(@PathVariable Integer id) {

		return service.findAll();

	}

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ComprasCliente save(@PathVariable Integer id, @RequestBody @Valid ComprasCliente compras) {
		Pacote pacote = pacoteService.findById(compras.getPacote().getId());
		Passagem passagem = passagemService.findById(compras.getPassagem().getId());
		return service.save(compras,pacote,passagem,id);
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}

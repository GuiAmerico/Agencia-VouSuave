package com.agencia.vousuave.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.server.ResponseStatusException;

import com.agencia.vousuave.entity.Cliente;
import com.agencia.vousuave.entity.ComprasCliente;
import com.agencia.vousuave.repository.ClienteRepository;
import com.agencia.vousuave.repository.ComprasClienteRepository;

@RestController
@RequestMapping("/api/compras")
public class ComprasClienteController {

	@Autowired
	private ComprasClienteRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ComprasCliente> getAllCompras() {
		System.out.println(repository.findAll());
		return repository.findAll();

	}

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ComprasCliente save(@PathVariable Integer id, @RequestBody @Valid ComprasCliente compras)
			throws ParseException {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

		compras.setCliente(cliente);
		compras.setPassagem(compras.getPassagem());
		System.out.println(compras.getPassagem());
		return repository.save(compras);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ComprasCliente update(@PathVariable Integer id, @RequestBody @Valid ComprasCliente compras) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compras não encontrada"));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}

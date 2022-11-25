package com.agencia.vousuave.controller;

<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java
=======
import java.text.ParseException;
import java.util.ArrayList;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java
import java.util.List;
import java.util.Optional;

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
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java
import com.agencia.vousuave.service.ComprasClienteService;
import com.agencia.vousuave.service.PacoteService;
import com.agencia.vousuave.service.PassagemService;

import lombok.RequiredArgsConstructor;
=======
import com.agencia.vousuave.repository.ClienteRepository;
import com.agencia.vousuave.repository.ComprasClienteRepository;
import com.agencia.vousuave.repository.PacoteRepository;
import com.agencia.vousuave.repository.PassagemRepository;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class ComprasClienteController {

<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java
	private final ComprasClienteService service;
	private final PacoteService pacoteService;
	private final PassagemService passagemService;
=======
	@Autowired
	private ComprasClienteRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PassagemRepository passagemRepository;
	@Autowired
	private PacoteRepository pacoteRepository;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ComprasCliente> getAllCompras(@PathVariable Integer id) {
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java

		return service.findAll();
=======
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		List<ComprasCliente> compras = cliente.getCompras();
		System.out.println(compras);
		return compras;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java

	}

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java
	public ComprasCliente save(@PathVariable Integer id, @RequestBody @Valid ComprasCliente compras) {
		Pacote pacote = pacoteService.findById(compras.getPacote().getId());
		Passagem passagem = passagemService.findById(compras.getPassagem().getId());
		return service.save(compras,pacote,passagem,id);
=======
	public ComprasCliente save(@PathVariable Integer id, @RequestBody @Valid ComprasCliente compras)
			throws ParseException {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		Passagem passagem = passagemRepository.findById(compras.getPassagem().getId()).orElse(null);
		Pacote pacote = pacoteRepository.findById(compras.getPacote().getId()).orElse(null);
		
		compras.setCliente(cliente);
		compras.setPassagem(passagem);
		compras.setPacote(pacote);
		System.out.println(compras);
		return repository.save(compras);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ComprasCliente update(@PathVariable Integer id, @RequestBody @Valid ComprasCliente compras) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compras não encontrada"));

>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/controller/ComprasClienteController.java
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}

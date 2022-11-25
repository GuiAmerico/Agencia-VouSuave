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

import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> getAllClientes () {
		return service.findAll();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario save(@RequestBody @Valid Usuario cliente) throws ParseException {
		return service.save(cliente);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)	
	public Usuario update( @PathVariable Integer id,@RequestBody @Valid Usuario cliente){
		return service.update(cliente, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void delete(@PathVariable Integer id){
		service.deleteById(id);
	}
}

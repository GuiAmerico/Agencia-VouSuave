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
import org.springframework.web.bind.annotation.RestController;

import com.agencia.vousuave.dto.UsuarioDTO;
import com.agencia.vousuave.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAllClientes () {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioDTO usuarioDTO) throws ParseException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update( @PathVariable Integer id,@RequestBody @Valid UsuarioDTO usuarioDTO){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(usuarioDTO, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario deletado com sucesso");
	}
}

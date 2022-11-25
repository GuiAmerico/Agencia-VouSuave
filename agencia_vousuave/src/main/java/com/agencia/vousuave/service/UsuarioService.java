package com.agencia.vousuave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository repository;
	
	public Usuario save(Usuario cliente) {
		return repository.save(cliente);
	}
	
	public Usuario update(Usuario cliente, Integer id) {
		existsById(id);
		cliente.setId(id);
		return save(cliente);
		
	}
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}
	
	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
	}
	
}

package com.agencia.vousuave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.entity.ComprasCliente;
import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.UsuarioRepository;
import com.agencia.vousuave.repository.ComprasClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComprasClienteService {
	
	private final ComprasClienteRepository repository;
	private final UsuarioRepository usuarioRepository;
	
	public ComprasCliente save(ComprasCliente compras,Pacote pacote, Passagem passagem, Integer id) {
		compras.setPacote(pacote);
		compras.setPassagem(passagem);
		Usuario cliente = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		compras.setCliente(cliente);
		return repository.save(compras);
	}
	
	
	public List<ComprasCliente> findAll(){
		return repository.findAll();
	}
	
	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}
	
	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Compras não encontrada"));
	}
	
}

package com.agencia.vousuave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.PacoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacoteService {
	
	private final PacoteRepository repository;
	
	public Pacote save(Pacote pacote) {
		return repository.save(pacote);
	}
	
	public Pacote update(Pacote pacote, Integer id) {
		existsById(id);
		pacote.setId(id);
		return save(pacote);
		
	}
	
	public List<Pacote> findAll(){
		return repository.findAll();
	}
	
	public Pacote findById(Integer id){
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado"));
	}
	
	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}
	
	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado"));
	}
	
}

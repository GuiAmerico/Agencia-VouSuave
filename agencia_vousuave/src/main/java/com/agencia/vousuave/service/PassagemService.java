package com.agencia.vousuave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.PassagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassagemService {
	
	private final PassagemRepository repository;
	
	public Passagem save(Passagem passagem) {
		return repository.save(passagem);
	}
	
	public Passagem update(Passagem passagem, Integer id) {
		existsById(id);
		passagem.setId(id);
		return save(passagem);
		
	}
	
	public List<Passagem> findAll(){
		return repository.findAll();
	}
	
	public Passagem findById(Integer id){
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passagem não encontrada"));
	}
	
	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}
	
	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passagem não encontrada"));
	}
	
}

package com.agencia.vousuave.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.PassagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassagemService {
	
	private final PassagemRepository repository;
	
	public PassagemDTO save(PassagemDTO passagemDTO) {
		
		Passagem passagem = new Passagem();
		BeanUtils.copyProperties(passagemDTO, passagem);
		repository.save(passagem);
		BeanUtils.copyProperties(passagem, passagemDTO);
		return passagemDTO;
	}
	
	public PassagemDTO update(PassagemDTO passagemDTO, Integer id) {
		existsById(id);
		passagemDTO.setId(id);
		Passagem passagem = new Passagem();
		BeanUtils.copyProperties(passagemDTO, passagem);
		repository.save(passagem);
		BeanUtils.copyProperties(passagem, passagemDTO);
		return passagemDTO;
		
	}
	
	public List<PassagemDTO> findAll(){
		List<PassagemDTO> passagens = new ArrayList<>();
		for(Passagem passagem : repository.findAll()) {
			PassagemDTO passagemDTO = new PassagemDTO();
			BeanUtils.copyProperties(passagem, passagemDTO);
			passagens.add(passagemDTO);
			
		}
		return passagens;
	}
	
	public PassagemDTO findById(Integer id){
		PassagemDTO passagemDTO = new PassagemDTO();
		Passagem passagem = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passagem não encontrada"));
		BeanUtils.copyProperties(passagem, passagemDTO);
		return passagemDTO; 
	}
	
	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}
	
	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passagem não encontrada"));
	}
	
}

package com.agencia.vousuave.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.PacoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacoteService {
	
	private final PacoteRepository repository;
	
	public PacoteDTO save(PacoteDTO pacoteDTO) {
		Pacote pacote = new Pacote();
		BeanUtils.copyProperties(pacoteDTO, pacote);
		
		repository.save(pacote);
		BeanUtils.copyProperties(pacote, pacoteDTO);
				
		return pacoteDTO;
	}
	
	public PacoteDTO update(PacoteDTO pacoteDTO, Integer id) {
		Pacote pacote = new Pacote();
		existsById(id);
		pacoteDTO.setId(id);
		BeanUtils.copyProperties(pacoteDTO, pacote);
		
		repository.save(pacote);
		return pacoteDTO;
		
	}
	
	public List<PacoteDTO> findAll(){
		
		List<PacoteDTO> pacotes = new ArrayList<>();
		for(Pacote pacote : repository.findAll()) {
			PacoteDTO pacoteDTO = new PacoteDTO();
			BeanUtils.copyProperties(pacote, pacoteDTO);
			pacotes.add(pacoteDTO);
		}
		return pacotes;
				
	}
	
	public PacoteDTO findById(Integer id){
		PacoteDTO pacoteDTO = new PacoteDTO();
		Pacote pacote = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado"));
		BeanUtils.copyProperties(pacote, pacoteDTO);
		return pacoteDTO;
	}
	
	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}
	
	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado"));
	}
	
}

package com.agencia.vousuave.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<PassagemDTO> findAll(Pageable pageable){
		Page<Passagem> passagens = repository.findAll(pageable);

		Page<PassagemDTO> passagensDTO = passagens.map(passagem -> new PassagemDTO(passagem));

		return passagensDTO;
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

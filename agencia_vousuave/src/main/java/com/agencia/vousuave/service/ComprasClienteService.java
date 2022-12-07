package com.agencia.vousuave.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.dto.ComprasClienteDTO;
import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.entity.ComprasCliente;
import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.ComprasClienteRepository;
import com.agencia.vousuave.repository.PacoteRepository;
import com.agencia.vousuave.repository.PassagemRepository;
import com.agencia.vousuave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComprasClienteService {
	
	private final ComprasClienteRepository repository;
	private final UsuarioRepository usuarioRepository;
	private final PacoteRepository pacoteRepository;
	private final PassagemRepository passagemRepository;
	
	public ComprasClienteDTO save(ComprasClienteDTO compras,Integer cliente_id) {
		Usuario cliente = usuarioRepository.findById(cliente_id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		compras.setCliente_id(cliente.getId());
		
		ComprasCliente comprasCliente = new ComprasCliente();
		Pacote pacote = pacoteRepository.findById(compras.getPacote().getId()).orElse(null);
		comprasCliente.setPacote(pacote);
		Passagem passagem = passagemRepository.findById(compras.getPassagem().getId()).orElse(null);
		comprasCliente.setPassagem(passagem);
		comprasCliente.setCliente(cliente);
		comprasCliente.setDataCompra(LocalDateTime.now());
		repository.save(comprasCliente);
		compras.setId(comprasCliente.getId());
		return compras;
	}
	
	
	public List<ComprasClienteDTO> findAll(Integer id){
		Usuario cliente = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		
		List<ComprasClienteDTO> compras = new ArrayList<>();
		for(ComprasCliente comprasCliente : cliente.getCompras()) {
			ComprasClienteDTO compra = new ComprasClienteDTO();
			compra.setId(comprasCliente.getId());
			PassagemDTO passagemDTO = new PassagemDTO();
			BeanUtils.copyProperties(comprasCliente.getPassagem(), passagemDTO);
			compra.setPassagem(passagemDTO);
			PacoteDTO pacoteDTO = new PacoteDTO();
			BeanUtils.copyProperties(comprasCliente.getPacote(), pacoteDTO);
			compra.setPacote(pacoteDTO);
			compra.setCliente_id(comprasCliente.getCliente().getId());
			
			compras.add(compra);
		}
		return compras;
	}
	
	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}
	
	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Compras não encontrada"));
	}
	
}

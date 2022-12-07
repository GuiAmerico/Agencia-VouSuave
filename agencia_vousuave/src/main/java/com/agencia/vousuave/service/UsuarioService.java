package com.agencia.vousuave.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.dto.EmailDTO;
import com.agencia.vousuave.dto.UsuarioDTO;
import com.agencia.vousuave.entity.Email;
import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final EmailService emailService;

	public UsuarioDTO save(UsuarioDTO usuarioDTO) {

		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDTO, usuario);

		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setStatus(true);
		sendEmail(usuario);
		repository.save(usuario);
		BeanUtils.copyProperties(usuario, usuarioDTO);

		return usuarioDTO;
	}

	private void sendEmail(Usuario usuario) {
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setEmailFrom("agenciavousuave@gmail.com");
		emailDTO.setEmailTo(usuario.getEmail());
		emailDTO.setOwnerRef(usuario.getNome());
		emailDTO.setSubject("Bem Vindo! Aproveite nossas ofertas");
		String text = String.format("O grande segredo de uma vida boa é descobrir qual é o seu destino. "
				+ "E realizá-lo. Que você possa encontrar o seu aqui. Seja bem-vindo %s! ", usuario.getNome());
		emailDTO.setText(text);

		Email email = new Email();
		BeanUtils.copyProperties(emailDTO, email);

		emailService.sendEmail(email);

	}

	public UsuarioDTO update(UsuarioDTO usuarioDTO, Integer id) {
		if (!repository.findById(id).isPresent()) {
			throw new ResourceNotFoundException("Usuario não encontrado");
		}
		usuarioDTO.setId(id);
		BeanUtils.copyProperties(save(usuarioDTO), usuarioDTO);

		return usuarioDTO;

	}

	public List<UsuarioDTO> findAll() {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		for(Usuario usuario : repository.findAll()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			BeanUtils.copyProperties(usuario, usuarioDTO);
			usuarios.add(usuarioDTO);
		}
		return usuarios;
		
	}

	public void deleteById(Integer id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
		usuario.setStatus(false);
	}

}

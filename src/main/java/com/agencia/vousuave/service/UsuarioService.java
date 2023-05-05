package com.agencia.vousuave.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.controller.UsuarioController;
import com.agencia.vousuave.dto.EmailDTO;
import com.agencia.vousuave.dto.LoginDTO;
import com.agencia.vousuave.dto.UsuarioDTO;
import com.agencia.vousuave.entity.Email;
import com.agencia.vousuave.entity.Role;
import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.enums.ERole;
import com.agencia.vousuave.exception.ResourceAlreadyExistsException;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.RoleRepository;
import com.agencia.vousuave.repository.UsuarioRepository;
import com.agencia.vousuave.response.JwtResponse;
import com.agencia.vousuave.security.jwt.JwtUtils;
import com.agencia.vousuave.service.impl.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final EmailService emailService;
	private final RoleRepository roleRepository;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;

	public UsuarioDTO save(UsuarioDTO usuarioDTO) {

		validarDados(usuarioDTO);

		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDTO, usuario);

		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setStatus(true);
		addRole(usuarioDTO, usuario);

		usuario.setSenha(encoder.encode(usuario.getSenha()));
		repository.save(usuario);

		if (usuario.getId() > 0) {
			sendEmail(usuario);
		}

		BeanUtils.copyProperties(usuario, usuarioDTO);

		usuarioDTO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioDTO.getId())).withSelfRel());

		return usuarioDTO;
	}

	private void validarDados(UsuarioDTO usuarioDTO) {
		if (repository.existsByEmail(usuarioDTO.getEmail())) {
			throw new ResourceAlreadyExistsException("Email já está sendo utilizado");
		}

		if (repository.existsByCelular(usuarioDTO.getCelular())) {
			throw new ResourceAlreadyExistsException("Celular já está sendo utilizado");
		}

	}

	private Set<Role> addRole(UsuarioDTO usuarioDTO, Usuario usuario) {

		Set<String> strRoles = usuarioDTO.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		usuario.setRoles(roles);
		return roles;
	}

	public JwtResponse authLogin(LoginDTO loginDTO) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, userDetails.getId(), userDetails.getNome(), userDetails.getEmail(), roles);

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
		Usuario usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDTO, usuario);
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());

		repository.save(usuario);
		return usuarioDTO;

	}

	public List<UsuarioDTO> findAll() {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		for (Usuario usuario : repository.findAll()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			BeanUtils.copyProperties(usuario, usuarioDTO);
			usuarios.add(usuarioDTO);
		}
		for (UsuarioDTO usuarioDTO : usuarios) {
			usuarioDTO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioDTO.getId())).withSelfRel());

		}
		return usuarios;

	}

	public void deleteById(Integer id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
		usuario.setStatus(false);
		repository.save(usuario);
	}

	public UsuarioDTO findById(Integer id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		BeanUtils.copyProperties(usuario, usuarioDTO);
		usuarioDTO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());

		return usuarioDTO;
	}

}

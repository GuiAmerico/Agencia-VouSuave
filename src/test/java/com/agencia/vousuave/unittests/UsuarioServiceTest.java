package com.agencia.vousuave.unittests;

import static com.agencia.vousuave.mocks.MockUsuario.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.agencia.vousuave.dto.EmailDTO;
import com.agencia.vousuave.dto.UsuarioDTO;
import com.agencia.vousuave.entity.Email;
import com.agencia.vousuave.entity.Role;
import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.enums.ERole;
import com.agencia.vousuave.exception.ResourceAlreadyExistsException;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.exception.ResourceNotValidException;
import com.agencia.vousuave.repository.RoleRepository;
import com.agencia.vousuave.repository.UsuarioRepository;
import com.agencia.vousuave.security.jwt.JwtUtils;
import com.agencia.vousuave.service.EmailService;
import com.agencia.vousuave.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService service;
	@Mock
	EmailService emailService;
	@Mock
	RoleRepository roleRepository;
	@Mock
	JwtUtils jwtUtils;
	@Mock
	AuthenticationManager authenticationManager;
	@Mock
	PasswordEncoder encoder;
	@Mock
	private UsuarioRepository repository;
	Email email;
	Role userRole;

	@BeforeEach
	void setup() {
		email = email(USUARIO);
		userRole = new Role();
		userRole.setId(1);
	}

	@Test
	public void saveUsuario_WithDataValid_ReturnsUsuario() {
		userRole.setName(ERole.ROLE_USER);

		when(repository.save(any(Usuario.class))).thenReturn(USUARIO);
		when(roleRepository.findByName(any(ERole.class))).thenReturn(Optional.of(userRole));

		UsuarioDTO sut = service.save(USUARIO_DTO);

		assertThat(sut).isEqualTo(USUARIO_DTO);
		verify(emailService).sendEmail(email);
	}

	@Test
	public void saveUsuario_WithDataInvalid_ThrowsResourceNotValidException() {
		when(roleRepository.findByName(any(ERole.class))).thenReturn(Optional.of(userRole));
		when(repository.save(any(Usuario.class))).thenThrow(ResourceNotValidException.class);

		assertThatThrownBy(() -> service.save(INVALID_USUARIO_DTO)).isInstanceOf(ResourceNotValidException.class);
		verifyNoInteractions(emailService);
	}
	
	@Test
	public void saveUsuario_WithDataAlreadyExists_ThrowsResourceAlreadyExistsException() {
		when(repository.existsByEmail(USUARIO.getEmail())).thenReturn(true);

		assertThatThrownBy(() -> service.save(USUARIO_DTO)).isInstanceOf(ResourceAlreadyExistsException.class);
	}


	@Test
	public void updateUsuario_WithDataValid_ReturnsUsuario() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(USUARIO));
		when(repository.save(any(Usuario.class))).thenReturn(USUARIO);

		UsuarioDTO sut = service.update(USUARIO_DTO, 1);

		assertThat(sut).isEqualTo(USUARIO_DTO);
	}

	@Test
	public void findUsuario_ById_ReturnsUsuario() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(USUARIO));

		UsuarioDTO sut = service.findById(anyInt());

		assertDoesNotThrow(() -> service.findById(anyInt()));
		assertThat(sut.getNome()).isEqualTo(USUARIO_DTO.getNome());
		assertThat(sut.getCpf()).isEqualTo(USUARIO_DTO.getCpf());
		assertThat(sut.getEmail()).isEqualTo(USUARIO_DTO.getEmail());
		assertThat(sut.getCelular()).isEqualTo(USUARIO_DTO.getCelular());

	}

	@Test
	public void findUsuario_ByUnexistingId_ThrowsResourceNotFoundException() {

		assertThatThrownBy(() -> service.findById(anyInt())).isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	public void deleteUsuario_WithExistingId_doesNotThrowAnyException() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(USUARIO));

		assertDoesNotThrow(() -> service.deleteById(anyInt()));
	}
	
	@Test
	public void deleteUsuario_WithUnexisting_ThrowResourceNotFoundException() {


		assertThatThrownBy(() -> service.deleteById(anyInt())).isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void findAllUsuarios_ReturnsUsuarios() {
		when(repository.findAll()).thenReturn(USUARIOS);
		List<UsuarioDTO> sut = service.findAll();

		assertThat(sut).hasSize(3);
		assertThat(sut.get(0)).isEqualTo(USUARIO_DTO_1);
		assertThat(sut.get(1)).isEqualTo(USUARIO_DTO_2);
		assertThat(sut.get(2)).isEqualTo(USUARIO_DTO_3);
			
	}
	
	Email email(Usuario usuario) {
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

		return email;
	}
}

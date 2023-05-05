package com.agencia.vousuave.unittests;

import static com.agencia.vousuave.mocks.MockComprasCliente.*;
import static com.agencia.vousuave.mocks.MockPacote.PACOTE;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGEM;
import static com.agencia.vousuave.mocks.MockUsuario.USUARIO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import com.agencia.vousuave.dto.ComprasClienteDTO;
import com.agencia.vousuave.entity.ComprasCliente;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.exception.ResourceNotValidException;
import com.agencia.vousuave.repository.ComprasClienteRepository;
import com.agencia.vousuave.repository.PacoteRepository;
import com.agencia.vousuave.repository.PassagemRepository;
import com.agencia.vousuave.repository.UsuarioRepository;
import com.agencia.vousuave.service.ComprasClienteService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class ComprasClienteServiceTest {

	@InjectMocks
	private ComprasClienteService service;
	@Mock
	UsuarioRepository usuarioRepository;
	@Mock
	PacoteRepository pacoteRepository;
	@Mock
	PassagemRepository passagemRepository;
	@Mock
	private ComprasClienteRepository repository;

	@Test
	@Order(0)
	public void saveComprasCliente_WithDataValid_ReturnsComprasCliente() {
		when(repository.save(any(ComprasCliente.class))).thenReturn(COMPRAS_CLIENTE);
		when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(USUARIO));

		ComprasClienteDTO sut = service.save(COMPRAS_CLIENTE_DTO,1);

		assertThat(sut).isEqualTo(COMPRAS_CLIENTE_DTO);
	}

	@Test
	@Order(1)
	public void saveComprasCliente_WithInexistingCliente_ThrowsResourceNotFoundException() {
		assertThatThrownBy(() -> service.save(COMPRAS_CLIENTE_DTO,anyInt())).isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	@Order(2)
	public void findComprasCliente_ByUnexistingId_ThrowsResourceNotFoundException() {

		assertThatThrownBy(() -> service.deleteById(anyInt())).isInstanceOf(ResourceNotFoundException.class);

	}
	
	@Test
	@Order(3)
	public void deleteComprasCliente_WithExistingId_doesNotThrowAnyException() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(COMPRAS_CLIENTE));
 
		assertDoesNotThrow(() -> service.deleteById(anyInt()));
	}

	@Test
	@Order(4)
	public void findAllPassagens_ReturnsPassagens() {
		when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(USUARIO));

		List<ComprasClienteDTO> sut = service.findAll(1);

		assertThat(sut).isEqualTo(COMPRAS_CLIENTES_DTO);
	}
	@Test
	@Order(5)
	public void findAllPassagens_WithInexistingCliente_ThrowsResourceNotFoundException() {
		assertThatThrownBy(() -> service.findAll(anyInt())).isInstanceOf(ResourceNotFoundException.class);
	}
}


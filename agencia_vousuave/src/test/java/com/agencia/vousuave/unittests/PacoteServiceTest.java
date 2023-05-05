package com.agencia.vousuave.unittests;

import static com.agencia.vousuave.mocks.MockPacote.INVALID_PACOTE;
import static com.agencia.vousuave.mocks.MockPacote.INVALID_PACOTE_DTO;
import static com.agencia.vousuave.mocks.MockPacote.PACOTE;
import static com.agencia.vousuave.mocks.MockPacote.PACOTES_PAGES;
import static com.agencia.vousuave.mocks.MockPacote.PACOTE_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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

import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.exception.ResourceNotValidException;
import com.agencia.vousuave.repository.PacoteRepository;
import com.agencia.vousuave.service.PacoteService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class PacoteServiceTest {

	@InjectMocks
	private PacoteService service;

	@Mock
	private PacoteRepository repository;

	@Test
	@Order(1)
	public void savePacote_WithDataValid_ReturnsPacote() {
		when(repository.save(any(Pacote.class))).thenReturn(PACOTE);

		PacoteDTO sut = service.save(PACOTE_DTO);

		assertThat(sut).isEqualTo(PACOTE_DTO);
	}

	@Test
	@Order(2)
	public void savePacote_WithDataInvalid_ThrowsResourceNotValidException() {
		when(repository.save(INVALID_PACOTE)).thenThrow(ResourceNotValidException.class);

		assertThatThrownBy(() -> service.save(INVALID_PACOTE_DTO)).isInstanceOf(ResourceNotValidException.class);
	}

	@Test
	@Order(3)
	public void updatePacote_WithDataValid_ReturnsPacote() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(PACOTE));
		when(repository.save(any(Pacote.class))).thenReturn(PACOTE);

		PACOTE_DTO.setDestino("Rio de Janeiro");
		PacoteDTO sut = service.update(PACOTE_DTO, 1);

		assertThat(sut).isEqualTo(PACOTE_DTO);
		assertThat(sut.getDestino()).isEqualTo("Rio de Janeiro");
	}

	@Test
	@Order(0)
	public void findPacote_ById_ReturnsPacote() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(PACOTE));

		PacoteDTO sut = service.findById(anyInt());
		
		assertDoesNotThrow(() -> service.findById(anyInt()));
		assertThat(sut).isEqualTo(PACOTE_DTO);
		assertThat(sut.getDestino()).isEqualTo(PACOTE_DTO.getDestino());
		assertThat(sut.getDiarias()).isEqualTo(PACOTE_DTO.getDiarias());
		assertThat(sut.getPreco()).isEqualTo(PACOTE_DTO.getPreco());
		assertThat(sut.getDesconto()).isEqualTo(PACOTE_DTO.getDesconto());
		assertThat(sut.getHotel()).isEqualTo(PACOTE_DTO.getHotel());
		assertThat(sut.getCaminhoImagem()).isEqualTo(PACOTE_DTO.getCaminhoImagem());
		assertThat(sut.isGuia()).isEqualTo(PACOTE_DTO.isGuia());
		assertThat(sut.isInternacional()).isEqualTo(PACOTE_DTO.isInternacional());

	}

	@Test
	@Order(4)
	public void findPacote_ByUnexistingId_ThrowsResourceNotFoundException() {
		when(repository.findById(anyInt())).thenThrow(ResourceNotFoundException.class);

		assertThatThrownBy(() -> service.findById(anyInt())).isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	@Order(5)
	public void deletePacote_WithExistingId_doesNotThrowAnyException() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(PACOTE));
 
		assertDoesNotThrow(() -> service.deleteById(anyInt()));
	}

	@Test
	@Order(6)
	public void findAllPassagens_ReturnsPassagens() {
		Pageable pageable = PageRequest.ofSize(3);
		when(repository.findAll(pageable)).thenReturn(PACOTES_PAGES);
		PagedModel<EntityModel<PacoteDTO>> sut = service.findAll(pageable);

		assertThat(sut).hasSize(3);
	}
}

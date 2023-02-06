package com.agencia.vousuave.unittests;

import static com.agencia.vousuave.mocks.MockPassagem.INVALID_PASSAGEM;
import static com.agencia.vousuave.mocks.MockPassagem.INVALID_PASSAGEM_DTO;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGEM;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGEM_DTO;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGENS_PAGES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.exception.ResourceNotValidException;
import com.agencia.vousuave.repository.PassagemRepository;
import com.agencia.vousuave.service.PassagemService;

@ExtendWith(MockitoExtension.class)
class PassagemServiceTest {

	@InjectMocks
	private PassagemService service;

	@Mock
	private PassagemRepository repository;

	@Test
	public void savePassagem_WithDataValid_ReturnsPassagem() {
		when(repository.save(any(Passagem.class))).thenReturn(PASSAGEM);

		PassagemDTO sut = service.save(PASSAGEM_DTO);

		assertThat(sut).isEqualTo(PASSAGEM_DTO);
	}

	@Test
	public void savePassagem_WithDataInvalid_ThrowsException() {
		when(repository.save(INVALID_PASSAGEM)).thenThrow(ResourceNotValidException.class);

		assertThatThrownBy(() -> service.save(INVALID_PASSAGEM_DTO)).isInstanceOf(ResourceNotValidException.class);
	}

	@Test
	public void updatePassagem_WithDataValid_ReturnsPassagem() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(PASSAGEM));
		when(repository.save(any(Passagem.class))).thenReturn(PASSAGEM);

		PASSAGEM_DTO.setDestino("Rio de Janeiro");
		PassagemDTO sut = service.update(PASSAGEM_DTO, 1);

		assertThat(sut).isEqualTo(PASSAGEM_DTO);
		assertThat(sut.getDestino()).isEqualTo("Rio de Janeiro");
	}

	@Test
	public void findPassagem_ById_ReturnsPassagem() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(PASSAGEM));

		PassagemDTO sut = service.findById(anyInt());

		assertDoesNotThrow(() -> service.findById(anyInt()));
		assertThat(sut).isEqualTo(PASSAGEM_DTO);
		assertThat(sut.getDestino()).isEqualTo(PASSAGEM.getDestino());
		assertThat(sut.getOrigem()).isEqualTo(PASSAGEM.getOrigem());
		assertThat(sut.getPreco()).isEqualTo(PASSAGEM.getPreco());
		assertThat(sut.getDesconto()).isEqualTo(PASSAGEM.getDesconto());
		assertThat(sut.getDisponibilidade()).isEqualTo(PASSAGEM.getDisponibilidade());
		assertThat(sut.getCaminhoImagem()).isEqualTo(PASSAGEM.getCaminhoImagem());
		assertThat(sut.getTiposPassagem()).isEqualTo(PASSAGEM.getTiposPassagem());

	}

	@Test
	public void findPassagem_ByUnexistingId_ThrowsException() {
		when(repository.findById(anyInt())).thenThrow(ResourceNotFoundException.class);

		assertThatThrownBy(() -> service.findById(anyInt())).isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	public void deletePassagem_WithExistingId_doesNotThrowAnyException() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(PASSAGEM));

		assertDoesNotThrow(() -> service.deleteById(anyInt()));
	}

	@Test
	public void findAllPassagens_ReturnsPassagens() {
		Pageable pageable = PageRequest.ofSize(3);
		when(repository.findAll(pageable)).thenReturn(PASSAGENS_PAGES);
		PagedModel<EntityModel<PassagemDTO>> sut = service.findAll(pageable);

		assertThat(sut).hasSize(3);

	}
}

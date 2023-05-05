package com.agencia.vousuave.integrationtests.passagem;

import static com.agencia.vousuave.mocks.MockPassagem.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.repository.PassagemRepository;

@DataJpaTest
public class PassagemRepositoryTest {

	@Autowired
	private PassagemRepository repository;

	@Autowired
	private TestEntityManager manager;

	@AfterEach
	public void afterEach() {
		PASSAGEM.setId(null);
	}

	@Test
	public void savePassagem_WithDataValid_ReturnsPassagem() throws Exception {
		Passagem passagem = repository.save(PASSAGEM);

		Passagem sut = manager.find(Passagem.class, passagem.getId());

		assertThat(sut.getId()).isNotNull();
		assertThat(sut.getDestino()).isEqualTo(PASSAGEM.getDestino());
		assertThat(sut.getOrigem()).isEqualTo(PASSAGEM.getOrigem());
		assertThat(sut.getPreco()).isEqualTo(PASSAGEM.getPreco());
		assertThat(sut.getCaminhoImagem()).isEqualTo(PASSAGEM.getCaminhoImagem());
		assertThat(sut.getTiposPassagem()).isEqualTo(PASSAGEM.getTiposPassagem());
		assertThat(sut.getDisponibilidade()).isEqualTo(PASSAGEM.getDisponibilidade());
		assertThat(sut.getDesconto()).isEqualTo(PASSAGEM.getDesconto());
	}

	@Test
	public void updatePassagem_WithDataValid_ReturnsPassagem() throws Exception {

		Passagem passagem = manager.find(Passagem.class, 1);
		passagem.setDestino("São Paulo");

		Passagem sut = repository.save(passagem);

		assertThat(sut.getDestino()).isEqualTo("São Paulo");

	}

	@Test
	public void savePassagem_WithDataInvalid_ThrowDataIntegrityViolationException() throws Exception {
		assertThatThrownBy(() -> repository.save(INVALID_PASSAGEM)).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	public void findPassagem_ById_ReturnsPassagem() {
		Passagem passagem = repository.findById(1).orElse(null);

		assertThat(passagem).isNotNull();
		assertThat(passagem).isEqualTo(PASSAGEM_1);
	}

	@Test
	public void findPassagem_ByUnexistingId_ReturnsNull() {
		Passagem passagem = repository.findById(100).orElse(null);

		assertThat(passagem).isNull();
	}

	@Test
	public void findAllPassagens_ReturnsPassagens() {
		Pageable pageable = PageRequest.of(0, 3);
		Page<Passagem> sut = repository.findAll(pageable);

		assertThat(sut).isNotNull();
		assertThat(sut.getSize()).isEqualTo(3);
		assertThat(sut.stream().toList().get(0)).isEqualTo(PASSAGEM_1);
		assertThat(sut.stream().toList().get(1)).isEqualTo(PASSAGEM_2);
		assertThat(sut.stream().toList().get(2)).isEqualTo(PASSAGEM_3);

	}

	@Test
	public void deletePassagem_WithExistingId_RemovesPassagemFromDatabase() {
		
		Passagem passagem = manager.find(Passagem.class, 3);
		
		repository.deleteById(passagem.getId());
		
		Passagem passagemDeleted =  manager.find(Passagem.class, 3);
		
		assertThat(passagemDeleted).isNull();
		
	}

	@Test
	public void deletePassagem_WithUnexistingId_ThrowsEmptyResultDataAccessException() {
		
		assertThatThrownBy(() -> repository.deleteById(100)).isInstanceOf(EmptyResultDataAccessException.class);
	}

}

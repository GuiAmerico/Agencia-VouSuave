package com.agencia.vousuave.integrationtests.pacote;

import static com.agencia.vousuave.mocks.MockPacote.*;
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

import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.repository.PacoteRepository;

@DataJpaTest
public class PacoteRepositoryTest {

	@Autowired
	private PacoteRepository repository;

	@Autowired
	private TestEntityManager manager;

	@AfterEach
	public void afterEach() {
		PACOTE.setId(null);
	}

	@Test
	public void savePacote_WithDataValid_ReturnsPacote() throws Exception {
		Pacote pacote = repository.save(PACOTE);

		Pacote sut = manager.find(Pacote.class, pacote.getId());

		assertThat(sut.getId()).isNotNull();
		assertThat(sut.getDestino()).isEqualTo(PACOTE.getDestino());
		assertThat(sut.getDiarias()).isEqualTo(PACOTE.getDiarias());
		assertThat(sut.getPreco()).isEqualTo(PACOTE.getPreco());
		assertThat(sut.getDesconto()).isEqualTo(PACOTE.getDesconto());
		assertThat(sut.getHotel()).isEqualTo(PACOTE.getHotel());
		assertThat(sut.getCaminhoImagem()).isEqualTo(PACOTE.getCaminhoImagem());
		assertThat(sut.isGuia()).isEqualTo(PACOTE.isGuia());
		assertThat(sut.isInternacional()).isEqualTo(PACOTE.isInternacional());	}

	@Test
	public void updatePacote_WithDataValid_ReturnsPacote() throws Exception {

		Pacote pacote = manager.find(Pacote.class, 1);
		pacote.setDestino("São Paulo");

		Pacote sut = repository.save(pacote);

		assertThat(sut.getDestino()).isEqualTo("São Paulo");

	}

	@Test
	public void savePacote_WithDataInvalid_ThrowDataIntegrityViolationException() throws Exception {
		assertThatThrownBy(() -> repository.save(INVALID_PACOTE)).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	public void findPacote_ById_ReturnsPacote() {
		Pacote pacote = repository.findById(1).orElse(null);

		assertThat(pacote).isNotNull();
		assertThat(pacote).isEqualTo(PACOTE_1);
	}

	@Test
	public void findPacote_ByUnexistingId_ReturnsNull() {
		Pacote pacote = repository.findById(100).orElse(null);

		assertThat(pacote).isNull();
	}

	@Test
	public void findAllPacotes_ReturnsPacotes() {
		Pageable pageable = PageRequest.of(0, 3);
		Page<Pacote> sut = repository.findAll(pageable);

		assertThat(sut).isNotNull();
		assertThat(sut.getSize()).isEqualTo(3);
		assertThat(sut.stream().toList().get(0)).isEqualTo(PACOTE_1);
		assertThat(sut.stream().toList().get(1)).isEqualTo(PACOTE_2);
		assertThat(sut.stream().toList().get(2)).isEqualTo(PACOTE_3);

	}

	@Test
	public void deletePacote_WithExistingId_RemovesPacoteFromDatabase() {
		
		Pacote pacote = manager.find(Pacote.class, 3);
		
		repository.deleteById(pacote.getId());
		
		Pacote pacoteDeleted =  manager.find(Pacote.class, 3);
		
		assertThat(pacoteDeleted).isNull();
		
	}

	@Test
	public void deletePacote_WithUnexistingId_ThrowsEmptyResultDataAccessException() {
		
		assertThatThrownBy(() -> repository.deleteById(100)).isInstanceOf(EmptyResultDataAccessException.class);
	}

}

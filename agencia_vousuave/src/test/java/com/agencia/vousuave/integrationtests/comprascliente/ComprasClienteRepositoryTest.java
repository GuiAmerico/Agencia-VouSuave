package com.agencia.vousuave.integrationtests.comprascliente;

import static com.agencia.vousuave.mocks.MockComprasCliente.INVALID_COMPRAS_CLIENTE;
import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTE;
import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTE_1;
import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTE_2;
import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTE_3;
import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

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

import com.agencia.vousuave.entity.ComprasCliente;
import com.agencia.vousuave.repository.ComprasClienteRepository;

@DataJpaTest
public class ComprasClienteRepositoryTest {

	@Autowired
	private ComprasClienteRepository repository;

	@Autowired
	private TestEntityManager manager;

	@AfterEach
	public void afterEach() {
		COMPRAS_CLIENTE.setId(null);
	}

	@Test
	public void saveComprasCliente_WithDataValid_ReturnsComprasCliente() throws Exception {
		ComprasCliente comprasCliente = repository.save(COMPRAS_CLIENTE);

		ComprasCliente sut = manager.find(ComprasCliente.class, comprasCliente.getId());

		assertThat(sut.getId()).isNotNull();
		assertThat(sut.getPacote()).isEqualTo(COMPRAS_CLIENTE.getPacote());
		assertThat(sut.getPassagem()).isEqualTo(COMPRAS_CLIENTE.getPassagem());
		assertThat(sut.getCliente()).isEqualTo(COMPRAS_CLIENTE.getCliente());
	}

	@Test
	public void findAllComprasClientes_ReturnsComprasClientes() {
		List<ComprasCliente> sut = repository.findAll();

		assertThat(sut).isNotNull();

	}

	@Test
	public void deleteComprasCliente_WithExistingId_RemovesComprasClienteFromDatabase() {

		ComprasCliente comprasCliente = manager.find(ComprasCliente.class, 3);

		repository.deleteById(comprasCliente.getId());

		ComprasCliente comprasClienteDeleted = manager.find(ComprasCliente.class, 3);

		assertThat(comprasClienteDeleted).isNull();

	}

	@Test
	public void deleteComprasCliente_WithUnexistingId_ThrowsEmptyResultDataAccessException() {

		assertThatThrownBy(() -> repository.deleteById(100)).isInstanceOf(EmptyResultDataAccessException.class);
	}

}

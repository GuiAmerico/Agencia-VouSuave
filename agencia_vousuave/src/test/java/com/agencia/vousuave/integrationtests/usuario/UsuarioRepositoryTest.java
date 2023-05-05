package com.agencia.vousuave.integrationtests.usuario;

import static com.agencia.vousuave.mocks.MockUsuario.INVALID_USUARIO;
import static com.agencia.vousuave.mocks.MockUsuario.USUARIO;
import static com.agencia.vousuave.mocks.MockUsuario.USUARIO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;

import com.agencia.vousuave.entity.Usuario;
import com.agencia.vousuave.repository.UsuarioRepository;

@DataJpaTest
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TestEntityManager manager;

	@AfterEach
	public void afterEach() {
		USUARIO.setId(null);
	}

	@Test
	public void saveUsuario_WithDataValid_ReturnsUsuario() throws Exception {
		Usuario usuario = repository.save(USUARIO);

		Usuario sut = manager.find(Usuario.class, usuario.getId());

		assertThat(sut.getId()).isNotNull();
		assertThat(sut.getNome()).isEqualTo(USUARIO_DTO.getNome());
		assertThat(sut.getCpf()).isEqualTo(USUARIO_DTO.getCpf());
		assertThat(sut.getEmail()).isEqualTo(USUARIO_DTO.getEmail());
		assertThat(sut.getCelular()).isEqualTo(USUARIO_DTO.getCelular());
	}

	@Test
	public void updateUsuario_WithDataValid_ReturnsUsuario() throws Exception {

		Usuario usuario = manager.find(Usuario.class, 1);
		usuario.setNome("Guilherme");

		Usuario sut = repository.save(usuario);

		assertThat(sut.getNome()).isEqualTo("Guilherme");

	}

	@Test
	public void saveUsuario_WithDataInvalid_ThrowConstraintViolationException() throws Exception {
		assertThatThrownBy(() -> repository.save(INVALID_USUARIO)).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	public void findUsuario_ById_ReturnsUsuario() {
		Usuario usuario = repository.findById(1).orElse(null);

		assertThat(usuario).isNotNull();
	}

	@Test
	public void findUsuario_ByUnexistingId_ReturnsNull() {
		Usuario usuario = repository.findById(100).orElse(null);

		assertThat(usuario).isNull();
	}

	@Test
	public void findAllPassagens_ReturnsPassagens() {
		List<Usuario> sut = repository.findAll();

		assertThat(sut).isNotNull();
		assertThat(sut.size()).isEqualTo(3);

	}

	@Test
	public void deleteUsuario_WithExistingId_RemovesUsuarioFromDatabase() {

		Usuario usuario = manager.find(Usuario.class, 3);

		repository.deleteById(usuario.getId());

		Usuario usuarioDeleted = manager.find(Usuario.class, 3);

		assertThat(usuarioDeleted).isNull();

	}

	@Test
	public void deleteUsuario_WithUnexistingId_ThrowsEmptyResultDataAccessException() {

		assertThatThrownBy(() -> repository.deleteById(100)).isInstanceOf(EmptyResultDataAccessException.class);
	}

}

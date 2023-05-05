package com.agencia.vousuave.mocks;

import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTES;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agencia.vousuave.dto.UsuarioDTO;
import com.agencia.vousuave.entity.Usuario;

public class MockUsuario {

	public static final UsuarioDTO USUARIO_DTO = UsuarioDTO.builder().id(1).dataNascimento(new Date()).nome("Usuario").cpf("99610496083")
			.celular("21999999999").email("usuario@email.com").senha("123456").build();

	public static final Usuario USUARIO = Usuario.builder().id(1).dataNascimento(new Date()).nome("Usuario").cpf("99610496083").celular("21999999999")
			.dataNascimento(new Date()).email("usuario@email.com").senha("123456").compras(COMPRAS_CLIENTES).build();

	public static final Usuario INVALID_USUARIO = Usuario.builder().nome(null).cpf(null).celular(null)
			.dataNascimento(null).email(null).senha(null).build();

	public static final UsuarioDTO INVALID_USUARIO_DTO = UsuarioDTO.builder().nome(null).cpf(null).celular(null)
			.dataNascimento(null).email(null).senha(null).build();

	public static final Usuario USUARIO_1 = Usuario.builder().nome("Usuario 1").cpf("88913862000")
			.celular("21888888888").dataNascimento(new Date()).email("usuario1@email.com").senha("654321").build();

	public static final Usuario USUARIO_2 = Usuario.builder().nome("Usuario 2").cpf("57677415067")
			.celular("21777777777").dataNascimento(new Date()).email("usuario2@email.com").senha("987654").build();

	public static final Usuario USUARIO_3 = Usuario.builder().nome("Usuario 3").cpf("99828656094")
			.celular("21666666666").dataNascimento(new Date()).email("usuario3@email.com").senha("000000").build();

	public static final UsuarioDTO USUARIO_DTO_1 = UsuarioDTO.builder().nome("Usuario 1").cpf("88913862000")
			.celular("21888888888").dataNascimento(new Date()).email("usuario1@email.com").senha("654321").build();

	public static final UsuarioDTO USUARIO_DTO_2 = UsuarioDTO.builder().nome("Usuario 2").cpf("57677415067")
			.celular("21777777777").dataNascimento(new Date()).email("usuario2@email.com").senha("987654").build();

	public static final UsuarioDTO USUARIO_DTO_3 = UsuarioDTO.builder().nome("Usuario 3").cpf("99828656094")
			.celular("21666666666").dataNascimento(new Date()).email("usuario3@email.com").senha("000000").build();

	public static final List<Usuario> USUARIOS = new ArrayList<>() {
		private static final long serialVersionUID = 1L;

		{
			add(USUARIO_1);
			add(USUARIO_2);
			add(USUARIO_3);
		}
	};


	public static final List<UsuarioDTO> USUARIOS_DTO = new ArrayList<>() {
		private static final long serialVersionUID = 1L;

		{
			add(USUARIO_DTO_1);
			add(USUARIO_DTO_2);
			add(USUARIO_DTO_3);
		}
	};

}

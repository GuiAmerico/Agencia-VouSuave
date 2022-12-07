package com.agencia.vousuave.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Integer id;
	@NotEmpty(message = "Campo Nome é obrigatório")
	private String nome;
	@NotEmpty(message = "Campo Email é obrigatório")
	@Email
	private String email;
	@NotEmpty(message = "Campo CPF é obrigatório")
	@CPF(message = "CPF Inválido")
	private String cpf;
	@NotEmpty(message = "Campo celular é obrigatório")
	private String celular;
	@NotNull(message = "Campo Data de nascimento obrigatório")
	private Date dataNascimento;
	@NotEmpty(message = "Campo Senha obrigatório")
	private String senha;
	private LocalDateTime dataCadastro;
	private boolean status;
	private List<Integer> compras;
	private Set<String> roles;
}

package com.agencia.vousuave.request;

import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class SignupRequest {

	private String nome;

	private String email;

	private String cpf;

	private String celular;

	private Date dataNascimento;

	private String senha;

	 private Set<String> role;
}

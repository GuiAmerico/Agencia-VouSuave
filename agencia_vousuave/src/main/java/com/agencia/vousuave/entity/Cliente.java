package com.agencia.vousuave.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@NotEmpty(message = "Campo Nome é obrigatório")
	@Column
	private String nome;
	
	@NotEmpty(message = "Campo Email é obrigatório")
	@Column
	private String email;
	
	@NotEmpty(message = "Campo CPF é obrigatório")
	@Column
	@CPF(message = "CPF Inválido")
	private String cpf;
	
	@NotEmpty(message = "Campo celular é obrigatório")
	@Column
	private String celular;
	
	@Column(name = "Data_Cadastro")
	private LocalDateTime dataCadastro;
	
	@NotEmpty(message = "Campo Data de nascimento obrigatório")
	@Column(name = "Data_Nascimento")
	private LocalDateTime dataNascimento;
	
	@Column
	private boolean status;
}




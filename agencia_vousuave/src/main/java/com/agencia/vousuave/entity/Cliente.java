package com.agencia.vousuave.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {
	
	public Cliente() {
		setStatus(true);
		setDataCadastro(LocalDateTime.now());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@NotEmpty(message = "Campo Nome é obrigatório")
	@Column(length = 110)
	private String nome;
	
	@NotEmpty(message = "Campo Email é obrigatório")
	@Column(length = 110)
	private String email;
	
	@NotEmpty(message = "Campo CPF é obrigatório")
	@Column(length = 11)
	@CPF(message = "CPF Inválido")
	private String cpf;
	
	@NotEmpty(message = "Campo celular é obrigatório")
	@Column(length = 11)
	private String celular;
	
	@NotNull(message = "Campo Data de nascimento obrigatório")
	@Column(name = "Data_Nascimento")
	private LocalDateTime dataNascimento;
	
	@NotEmpty(message = "Campo Senha obrigatório")
	@Column(length = 20)
	private String senha;
	
	@Column(name = "Data_Cadastro")
	private LocalDateTime dataCadastro;
	
	@Column
	private boolean status;
	
	@OneToMany(mappedBy = "cliente")
	private List<ComprasCliente> compras;
}




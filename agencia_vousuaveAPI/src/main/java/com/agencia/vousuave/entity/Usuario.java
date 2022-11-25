package com.agencia.vousuave.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

	public Usuario(String nome, String email, String cpf, String celular, Date dataNascimento, String senha) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.celular = celular;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@NotEmpty(message = "Campo Nome é obrigatório")
	@Column(length = 110, nullable = false)
	private String nome;

	@NotEmpty(message = "Campo Email é obrigatório")
	@Column(length = 110, nullable = false)
	private String email;

	@NotEmpty(message = "Campo CPF é obrigatório")
	@Column(length = 11, unique = true, nullable = false)
	@CPF(message = "CPF Inválido")
	private String cpf;

	@NotEmpty(message = "Campo celular é obrigatório")
	@Column(length = 11, unique = true, nullable = false)
	private String celular;

	@NotNull(message = "Campo Data de nascimento obrigatório")
	@Column(name = "Data_Nascimento", nullable = false)
	private Date dataNascimento;

	@NotEmpty(message = "Campo Senha obrigatório")
	@Column(length = 20, unique = true, nullable = false)
	private String senha;

	@Column(name = "Data_Cadastro")
	private LocalDateTime dataCadastro;

	@Column
	private boolean status;

	@OneToMany(mappedBy = "cliente")
	private List<ComprasCliente> compras;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}

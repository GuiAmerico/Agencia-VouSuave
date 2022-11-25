package com.agencia.vousuave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/entity/Pacote.java
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
=======
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/entity/Pacote.java
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/entity/Pacote.java
public class Pacote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Campo Destino é obrigatório")
	@Column(length = 50, nullable = false)
	private String destino;

	@NotNull(message = "Campo Preço é obrigatório")
	@Column(nullable = false)
	private double preco;

	@NotNull(message = "Campo Desconto é obrigatório")
	@Column(nullable = false)
	@Min(0)
	@Max(1)
	private double desconto;

	@NotNull(message = "Campo Diarias é obrigatório")
	@Column(name = "diaria", nullable = false)
=======
public class Pacote{
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo Destino é obrigatório")
	@Column
	private String destino;
	
	@NotNull(message = "Campo Preço é obrigatório")
	@Column
	private double preco;
	

	@NotNull(message = "Campo Preço é obrigatório")
	@Column
	private double desconto;
	
	@NotNull(message = "Campo Diarias é obrigatório")
	@Column(name = "diaria")
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/entity/Pacote.java
	private int diarias;

	@NotEmpty(message = "Campo Hotel é obrigatório")
	@Column(length = 40, nullable = false, unique = true)
	private String hotel;
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/entity/Pacote.java

	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	@Column(name = "caminho", nullable = false)
	private String caminhoImagem;

	@NotNull(message = "Campo Guia de Turismo é obrigatório")
	@Column(name = "guia_turismo", nullable = false)
	private boolean guia;

	@NotNull(message = "Campo Internacional é obrigatório")
	@Column(nullable = false)
	private boolean internacional;

=======
	
	@NotNull(message = "Campo Guia de Turismo é obrigatório")
	@Column(name = "guia_turismo")
	private boolean guia;
	
	@NotNull(message = "Campo Internacional é obrigatório")
	@Column
	private boolean internacional;
	
	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	@Column(name = "caminho")
	private String caminhoImagem;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/entity/Pacote.java
}

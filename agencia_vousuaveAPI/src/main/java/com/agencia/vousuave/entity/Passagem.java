package com.agencia.vousuave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.agencia.vousuave.enums.TiposPassagem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Passagem {

	public Passagem() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Campo Origem é obrigatório")
	@Column(length = 110, nullable = false)
	private String origem;

	@NotEmpty(message = "Campo Destino é obrigatório")
	@Column(length = 110, nullable = false)
	private String destino;

	@NotNull(message = "Campo Preço é obrigatório")
	@Column(nullable = false)
	private double preco;

	@NotNull(message = "Campo Desconto é obrigatório")
	@Column(nullable = false)
	@Min(0)
	@Max(1)
	private double desconto;

	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	@Column(name = "caminho")
	private String caminhoImagem;

<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/entity/Passagem.java
	@Column(name = "Tipo_Passagem", nullable = false)
	@NotNull(message = "Campo Tipo de Passagem é obrigatório")
	@Enumerated(EnumType.STRING)
	private TiposPassagem tiposPassagem;

	@Column(length = 10)
=======
	@Column(name = "tipo_passagem")
	@NotNull(message = "Campo Tipo de Passagem é obrigatório")
	@Enumerated(EnumType.STRING)
	private TiposPassagem tiposPassagem;
	
	@Column
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/entity/Passagem.java
	private String disponibilidade;
}

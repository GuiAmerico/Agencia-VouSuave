package com.agencia.vousuave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Passagem {

	public Passagem() {

	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Campo Origem é obrigatório")
	@Column(length = 110)
	private String origem;

	@NotEmpty(message = "Campo Destino é obrigatório")
	@Column(length = 110)
	private String destino;

	@NotNull(message = "Campo Preço é obrigatório")
	@Column
	private double preco;

	@NotNull(message = "Campo Desconto é obrigatório")
	@Column
	private double desconto;

	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	@Column(name = "caminho")
	private String caminhoImagem;

	@Column(name = "id_tp")
	@NotNull(message = "Campo Tipo de Passagem é obrigatório")
	@Enumerated(EnumType.STRING)
	private TiposPassagem tiposPassagem;
}

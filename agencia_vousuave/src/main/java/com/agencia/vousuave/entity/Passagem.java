package com.agencia.vousuave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

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

	@NotEmpty(message = "Campo Preço é obrigatório")
	@Column
	private double preco;

	@NotEmpty(message = "Campo Desconto é obrigatório")
	@Column
	private double desconto;

	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	@Column(name = "caminho")
	private String caminhoImagem;

	@Column(name = "id_tp")
	@NotEmpty(message = "Campo Tipo de Passagem é obrigatório")
	private TiposPassagem tiposPassagem;
}

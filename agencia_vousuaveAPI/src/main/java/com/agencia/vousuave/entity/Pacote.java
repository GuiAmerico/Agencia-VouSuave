package com.agencia.vousuave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
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
	private int diarias;
	
	@NotEmpty(message = "Campo Hotel é obrigatório")
	@Column
	private String hotel;
	
	@NotNull(message = "Campo Guia de Turismo é obrigatório")
	@Column(name = "guia_turismo")
	private boolean guia;
	
	@NotNull(message = "Campo Internacional é obrigatório")
	@Column
	private boolean internacional;
	
	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	@Column(name = "caminho")
	private String caminhoImagem;
}

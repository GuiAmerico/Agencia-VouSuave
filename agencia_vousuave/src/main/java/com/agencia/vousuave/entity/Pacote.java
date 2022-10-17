package com.agencia.vousuave.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pacote {
		
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty(message = "Campo Destino é obrigatório")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "destino",referencedColumnName = "destino")
	private Passagem destino;
	
	@NotEmpty(message = "Campo Preço é obrigatório")
	@Column
	private double preco;
	
	@NotEmpty(message = "Campo Diarias é obrigatório")
	@Column(name = "diaria")
	private int diarias;
	
	@NotEmpty(message = "Campo Hotel é obrigatório")
	@Column
	private String hotel;
	
	@NotEmpty(message = "Campo Guia de Turismo é obrigatório")
	@Column(name = "guia_turismo")
	private boolean guia;
	
	@NotEmpty(message = "Campo Internacional é obrigatório")
	@Column
	private boolean internacional;
	
}

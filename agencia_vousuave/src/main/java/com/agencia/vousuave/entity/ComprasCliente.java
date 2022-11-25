package com.agencia.vousuave.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Compras_Cliente")
public class ComprasCliente {

	public ComprasCliente() {
		this.setDataCompra(LocalDateTime.now());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private Passagem passagem;

	@OneToOne(cascade = CascadeType.ALL)
	private Pacote pacote;

	@Column(name = "data_compra")
	private LocalDateTime dataCompra;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Usuario cliente;
}

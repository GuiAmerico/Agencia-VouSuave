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
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/entity/ComprasCliente.java

	@OneToOne(cascade = CascadeType.ALL)
	private Passagem passagem;

	@OneToOne(cascade = CascadeType.ALL)
=======
	
	
	@JoinColumn(name = "id_passagem")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Passagem passagem;

	@JoinColumn(name = "id_pacote")
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/entity/ComprasCliente.java
	private Pacote pacote;

	@Column(name = "data_compra")
	private LocalDateTime dataCompra;
<<<<<<< HEAD:agencia_vousuave/src/main/java/com/agencia/vousuave/entity/ComprasCliente.java

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Usuario cliente;
=======
	
	@JoinColumn(name = "id_cliente")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Cliente cliente;
>>>>>>> main:agencia_vousuaveAPI/src/main/java/com/agencia/vousuave/entity/ComprasCliente.java
}

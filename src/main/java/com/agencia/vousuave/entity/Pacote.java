package com.agencia.vousuave.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Pacote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false)
	private String destino;

	@Column(nullable = false)
	private double preco;

	@Column(nullable = false)
	@Min(0)
	@Max(1)
	private double desconto;

	@Column(name = "diaria", nullable = false)
	private int diarias;

	@Column(length = 40, nullable = false)
	private String hotel;

	@Column(name = "caminho", nullable = false)
	private String caminhoImagem;

	@Column(name = "guia_turismo", nullable = false)
	private boolean guia;

	@Column(nullable = false)
	private boolean internacional;

}

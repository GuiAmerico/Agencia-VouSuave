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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Passagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 110, nullable = false)
	private String origem;

	@Column(length = 110, nullable = false)
	private String destino;

	@Column(nullable = false)
	private double preco;

	@Column(nullable = false)
	@Min(0)
	@Max(1)
	private double desconto;

	@Column(name = "caminho")
	private String caminhoImagem;

	@Column(name = "Tipo_Passagem", nullable = false)
	@Enumerated(EnumType.STRING)
	private TiposPassagem tiposPassagem;

	@Column(length = 10)
	private String disponibilidade;
}

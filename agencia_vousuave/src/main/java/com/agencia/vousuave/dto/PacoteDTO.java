package com.agencia.vousuave.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PacoteDTO {

	private Integer id;
	@NotNull(message = "Campo Destino é obrigatório")
	private String destino;
	@NotNull(message = "Campo Preço é obrigatório")
	private double preco;
	@NotNull(message = "Campo Desconto é obrigatório")
	@Min(0)
	@Max(1)
	private double desconto;
	@NotNull(message = "Campo Diarias é obrigatório")
	private int diarias;
	@NotEmpty(message = "Campo Hotel é obrigatório")
	private String hotel;
	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	private String caminhoImagem;
	@NotNull(message = "Campo Guia de Turismo é obrigatório")
	private boolean guia;
	@NotNull(message = "Campo Internacional é obrigatório")
	private boolean internacional;

}

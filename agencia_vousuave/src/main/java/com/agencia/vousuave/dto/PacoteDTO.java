package com.agencia.vousuave.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.agencia.vousuave.entity.Pacote;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PacoteDTO {

	public PacoteDTO(Pacote pacote) {
		this.id = pacote.getId();
		this.destino = pacote.getDestino();
		this.preco = pacote.getPreco();
		this.desconto = pacote.getDesconto();
		this.diarias = pacote.getDiarias();
		this.hotel = pacote.getHotel();
		this.caminhoImagem = pacote.getCaminhoImagem();
		this.guia = pacote.isGuia();
		this.internacional = pacote.isInternacional();
	}
	
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

package com.agencia.vousuave.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.enums.TiposPassagem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PassagemDTO extends RepresentationModel<PassagemDTO>{

	public PassagemDTO(Passagem passagem) {
		this.id = passagem.getId();
		this.origem = passagem.getOrigem();
		this.destino = passagem.getDestino();
		this.preco = passagem.getPreco();
		this.desconto = passagem.getDesconto();
		this.caminhoImagem = passagem.getCaminhoImagem();
		this.tiposPassagem = passagem.getTiposPassagem();
		this.disponibilidade = passagem.getDisponibilidade();
	}
	
	private Integer id;
	@NotEmpty(message = "Campo Origem é obrigatório")
	private String origem;
	@NotEmpty(message = "Campo Destino é obrigatório")
	private String destino;
	@NotNull(message = "Campo Preço é obrigatório")
	private double preco;
	@NotNull(message = "Campo Desconto é obrigatório")
	@Min(0)
	@Max(1)
	private double desconto;
	@NotEmpty(message = "Campo Caminho da Imagem é obrigatório")
	private String caminhoImagem;
	@NotNull(message = "Campo Tipo de Passagem é obrigatório")
	private TiposPassagem tiposPassagem;
	private String disponibilidade;
}

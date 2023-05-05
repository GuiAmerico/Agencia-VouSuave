package com.agencia.vousuave.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({ "id", "passagem", "pacote", "cliente_id" })
public class ComprasClienteDTO extends RepresentationModel<ComprasClienteDTO> {

	private Integer id;
	private PassagemDTO passagem;
	private PacoteDTO pacote;
	private Integer cliente_id;
}

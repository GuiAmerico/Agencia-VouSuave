package com.agencia.vousuave.dto;

import lombok.Data;

@Data
public class ComprasClienteDTO {

	private Integer id;
	private PassagemDTO passagem;
	private PacoteDTO pacote;
	private Integer cliente_id;
}

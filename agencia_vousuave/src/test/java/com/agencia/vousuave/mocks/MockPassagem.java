package com.agencia.vousuave.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.enums.TiposPassagem;

public class MockPassagem {

	public static final PassagemDTO PASSAGEM_DTO = PassagemDTO.builder().origem("Origem").destino("Destino").preco(1000)
			.desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null).tiposPassagem(TiposPassagem.AVIAO)
			.build();

	public static final Passagem PASSAGEM = Passagem.builder().origem("Origem").destino("Destino").preco(1000)
			.desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null).tiposPassagem(TiposPassagem.AVIAO)
			.build();

	public static final Passagem INVALID_PASSAGEM = Passagem.builder().origem("").destino("").preco(0).desconto(0)
			.caminhoImagem("").disponibilidade("").tiposPassagem(null).build();

	public static final PassagemDTO INVALID_PASSAGEM_DTO = PassagemDTO.builder().origem("").destino("").preco(0)
			.desconto(0).caminhoImagem("").disponibilidade("").tiposPassagem(null).build();

	public static final Passagem PASSAGEM_1 = Passagem.builder().id(1).origem("Origem").destino("Destino").preco(1000)
			.desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null).tiposPassagem(TiposPassagem.AVIAO)
			.build();

	public static final Passagem PASSAGEM_2 = Passagem.builder().id(2).origem("Origem").destino("Destino").preco(1000)
			.desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null).tiposPassagem(TiposPassagem.AVIAO)
			.build();

	public static final Passagem PASSAGEM_3 = Passagem.builder().id(3).origem("Origem").destino("Destino").preco(1000)
			.desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null).tiposPassagem(TiposPassagem.AVIAO)
			.build();

	public static final PassagemDTO PASSAGEM_DTO_1 = PassagemDTO.builder().origem("Origem").destino("Destino")
			.preco(1000).desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null)
			.tiposPassagem(TiposPassagem.AVIAO).build();

	public static final PassagemDTO PASSAGEM_DTO_2 = PassagemDTO.builder().origem("Origem").destino("Destino")
			.preco(1000).desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null)
			.tiposPassagem(TiposPassagem.AVIAO).build();

	public static final PassagemDTO PASSAGEM_DTO_3 = PassagemDTO.builder().origem("Origem").destino("Destino")
			.preco(1000).desconto(0.05).caminhoImagem("CaminhoImagem").disponibilidade(null)
			.tiposPassagem(TiposPassagem.AVIAO).build();

	private static final List<Passagem> PASSAGENS = new ArrayList<>() {
		{
			add(PASSAGEM_1);
			add(PASSAGEM_2);
			add(PASSAGEM_3);
		}
	};

	public static final Page<Passagem> PASSAGENS_PAGES = new PageImpl<>(PASSAGENS);

	public static final Page<PassagemDTO> PASSAGENS_DTO = PASSAGENS_PAGES.map(passagem -> new PassagemDTO(passagem));

}

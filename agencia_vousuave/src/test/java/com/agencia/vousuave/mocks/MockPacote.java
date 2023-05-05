package com.agencia.vousuave.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.entity.Pacote;

public class MockPacote {

	public static final PacoteDTO PACOTE_DTO = PacoteDTO.builder().destino("Destino").diarias(3).preco(2000)
			.desconto(0.1).caminhoImagem("CaminhoImagem").hotel("Hotel 1").guia(false).internacional(false).build();

	public static final Pacote PACOTE = Pacote.builder().destino("Destino").diarias(3).preco(2000)
			.desconto(0.1).caminhoImagem("CaminhoImagem").hotel("Hotel 1").guia(false).internacional(false).build();

	public static final Pacote INVALID_PACOTE = Pacote.builder().destino(null).diarias(0).preco(0)
			.desconto(0).caminhoImagem(null).hotel(null).guia(false).internacional(false).build();

	public static final PacoteDTO INVALID_PACOTE_DTO = PacoteDTO.builder().destino("").diarias(0).preco(0)
			.desconto(-1).caminhoImagem("").hotel("").guia(false).internacional(false).build();


	public static final Pacote PACOTE_1 = Pacote.builder().id(1).destino("Destino").diarias(1).preco(1000)
			.desconto(0.1).caminhoImagem("CaminhoImagem").hotel("Hotel 1").guia(false).internacional(false).build();

	public static final Pacote PACOTE_2 = Pacote.builder().id(2).destino("Destino").diarias(2).preco(2000)
			.desconto(0.2).caminhoImagem("CaminhoImagem").hotel("Hotel 2").guia(false).internacional(false).build();


	public static final Pacote PACOTE_3 = Pacote.builder().id(3).destino("Destino").diarias(3).preco(3000)
			.desconto(0.3).caminhoImagem("CaminhoImagem").hotel("Hotel 3").guia(false).internacional(false).build();


	private static final List<Pacote> PACOTES = new ArrayList<>() {
		private static final long serialVersionUID = 1L;

		{
			add(PACOTE_1);
			add(PACOTE_2);
			add(PACOTE_3);
		}
	};

	public static final Page<Pacote> PACOTES_PAGES = new PageImpl<>(PACOTES);

	public static final Page<PacoteDTO> PACOTES_DTO = PACOTES_PAGES.map(pacote -> new PacoteDTO(pacote));

}

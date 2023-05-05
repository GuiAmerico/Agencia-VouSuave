package com.agencia.vousuave.mocks;

import static com.agencia.vousuave.mocks.MockPacote.PACOTE;
import static com.agencia.vousuave.mocks.MockPacote.PACOTE_DTO;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGEM;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGEM_DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.agencia.vousuave.dto.ComprasClienteDTO;
import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.entity.ComprasCliente;
import com.agencia.vousuave.entity.Usuario;

public class MockComprasCliente {

	public static final ComprasClienteDTO COMPRAS_CLIENTE_DTO = ComprasClienteDTO.builder().pacote(PACOTE_DTO)
			.passagem(PASSAGEM_DTO).cliente_id(1).build();
	public static final ComprasCliente COMPRAS_CLIENTE = ComprasCliente.builder().pacote(PACOTE).passagem(PASSAGEM)
			.cliente(new Usuario()).build();
	

	public static final ComprasCliente INVALID_COMPRAS_CLIENTE = ComprasCliente.builder().pacote(null).passagem(null)
			.cliente(null).build();
	public static final ComprasClienteDTO INVALID_COMPRAS_CLIENTE_DTO = ComprasClienteDTO.builder().pacote(null)
			.passagem(null).cliente_id(null).build();

	
	public static final ComprasCliente COMPRAS_CLIENTE_1 = ComprasCliente.builder().id(1).pacote(PACOTE).passagem(PASSAGEM)
			.cliente(new Usuario()).build();
	public static final ComprasCliente COMPRAS_CLIENTE_2 = ComprasCliente.builder().id(2).pacote(PACOTE).passagem(PASSAGEM)
			.cliente(new Usuario()).build();
	public static final ComprasCliente COMPRAS_CLIENTE_3 = ComprasCliente.builder().id(3).pacote(PACOTE).passagem(PASSAGEM)
			.cliente(new Usuario()).build();

	
	public static final List<ComprasCliente> COMPRAS_CLIENTES = new ArrayList<>() {
		private static final long serialVersionUID = 1L;

		{
			add(COMPRAS_CLIENTE_1);
			add(COMPRAS_CLIENTE_2);
			add(COMPRAS_CLIENTE_3);
		}
	};

//	public static final Page<ComprasCliente> COMPRAS_CLIENTES_PAGES = new PageImpl<>(COMPRAS_CLIENTES);

	public static final List<ComprasClienteDTO> COMPRAS_CLIENTES_DTO = COMPRAS_CLIENTES.stream()
			.map(comprasCliente -> {
				ComprasClienteDTO compra = new ComprasClienteDTO();
				compra.setId(comprasCliente.getId());
				PassagemDTO passagemDTO = new PassagemDTO();
				BeanUtils.copyProperties(comprasCliente.getPassagem(), passagemDTO);
				compra.setPassagem(passagemDTO);
				PacoteDTO pacoteDTO = new PacoteDTO();
				BeanUtils.copyProperties(comprasCliente.getPacote(), pacoteDTO);
				compra.setPacote(pacoteDTO);
				compra.setCliente_id(comprasCliente.getCliente().getId());
				
				return compra;
			}).toList();

}

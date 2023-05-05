package com.agencia.vousuave.integrationtests.comprascliente;

import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTES_DTO;
import static com.agencia.vousuave.mocks.MockComprasCliente.COMPRAS_CLIENTE_DTO;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.agencia.vousuave.controller.ComprasClienteController;
import com.agencia.vousuave.dto.ComprasClienteDTO;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.security.jwt.AuthEntryPointJwt;
import com.agencia.vousuave.security.jwt.JwtUtils;
import com.agencia.vousuave.service.ComprasClienteService;
import com.agencia.vousuave.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ComprasClienteController.class)
public class ComprasClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private ComprasClienteService service;

	@MockBean
	private UserDetailsServiceImpl impl;

	@MockBean
	private AuthEntryPointJwt unauthoriezedHandler;

	@MockBean
	private JwtUtils jwtUtils;

	@Test
	public void saveComprasCliente_WithDataValid_ReturnsComprasClienteAndStatusCreated() throws Exception {
		when(service.save(any(ComprasClienteDTO.class), anyInt())).thenReturn(COMPRAS_CLIENTE_DTO);
		String content = mapper.writeValueAsString(COMPRAS_CLIENTE_DTO);
		String uri = "/api/compras/1";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$").value(COMPRAS_CLIENTE_DTO));
	}

	@Test
	public void saveComprasCliente_WithInexistentCliente_ReturnsNotFound() throws Exception {
		when(service.save(any(ComprasClienteDTO.class), anyInt())).thenThrow(ResourceNotFoundException.class);
		;

		String content = mapper.writeValueAsString(COMPRAS_CLIENTE_DTO);
		String uri = "/api/compras/1";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void findAllPacotes_ReturnsListComprasClienteAndStatusOk() throws Exception {
		String jsonData = "[{\"id\":1,\"passagem\":{\"origem\":\"Origem\",\"destino\":\"Destino\",\"preco\":1000.0,\"desconto\":0.05,\"caminhoImagem\":\"CaminhoImagem\",\"tiposPassagem\":\"AVIAO\",\"links\":[]},\"pacote\":{\"id\":null,\"destino\":\"Destino\",\"preco\":2000.0,\"desconto\":0.1,\"diarias\":3,\"hotel\":\"Hotel 1\",\"caminhoImagem\":\"CaminhoImagem\",\"guia\":false,\"internacional\":false,\"links\":[]},\"cliente_id\":null,\"links\":[]},{\"id\":2,\"passagem\":{\"origem\":\"Origem\",\"destino\":\"Destino\",\"preco\":1000.0,\"desconto\":0.05,\"caminhoImagem\":\"CaminhoImagem\",\"tiposPassagem\":\"AVIAO\",\"links\":[]},\"pacote\":{\"id\":null,\"destino\":\"Destino\",\"preco\":2000.0,\"desconto\":0.1,\"diarias\":3,\"hotel\":\"Hotel 1\",\"caminhoImagem\":\"CaminhoImagem\",\"guia\":false,\"internacional\":false,\"links\":[]},\"cliente_id\":null,\"links\":[]},{\"id\":3,\"passagem\":{\"origem\":\"Origem\",\"destino\":\"Destino\",\"preco\":1000.0,\"desconto\":0.05,\"caminhoImagem\":\"CaminhoImagem\",\"tiposPassagem\":\"AVIAO\",\"links\":[]},\"pacote\":{\"id\":null,\"destino\":\"Destino\",\"preco\":2000.0,\"desconto\":0.1,\"diarias\":3,\"hotel\":\"Hotel 1\",\"caminhoImagem\":\"CaminhoImagem\",\"guia\":false,\"internacional\":false,\"links\":[]},\"cliente_id\":null,\"links\":[]}]";

		when(service.findAll(1)).thenReturn(COMPRAS_CLIENTES_DTO);
		String content = mapper.writeValueAsString(COMPRAS_CLIENTE_DTO);
		String uri = "/api/compras/1";

		mockMvc.perform(get(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("*", hasSize(3)))
				.andExpect(content().json(jsonData)).andExpect(status().isOk());

	}

	@Test
	public void findAllComprasCliente_WithInexistentCliente_ReturnsNotFound() throws Exception {
		when(service.findAll(anyInt())).thenThrow(ResourceNotFoundException.class);

		String uri = "/api/compras/1";
		mockMvc.perform(get(uri)).andExpect(status().isNotFound());
	}

	@Test
	public void deleteComprasCliente_ById_ReturnsNoContent() throws Exception {
		String uri = "/api/compras/1";

		mockMvc.perform(delete(uri)).andExpect(status().isNoContent());

	}

}

package com.agencia.vousuave.integrationtests.passagem;

import static com.agencia.vousuave.mocks.MockPassagem.INVALID_PASSAGEM_DTO;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGEM_DTO;
import static com.agencia.vousuave.mocks.MockPassagem.PASSAGENS_DTO;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.agencia.vousuave.controller.PassagemController;
import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.mocks.MockPassagem;
import com.agencia.vousuave.security.jwt.AuthEntryPointJwt;
import com.agencia.vousuave.security.jwt.JwtUtils;
import com.agencia.vousuave.service.PassagemService;
import com.agencia.vousuave.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PassagemController.class)
public class PassagemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private PassagemService service;

	@MockBean
	private UserDetailsServiceImpl impl;

	@MockBean
	private AuthEntryPointJwt unauthoriezedHandler;

	@MockBean
	private JwtUtils jwtUtils;

	@MockBean
	private MockPassagem mockPassagem;

	private static PagedModel<EntityModel<PassagemDTO>> PASSAGENS_PAGE_DTO;

	@BeforeAll
	public static void setup() {
		HttpServletRequest mockRequest = new MockHttpServletRequest();
		ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
		RequestContextHolder.setRequestAttributes(servletRequestAttributes);
		HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();
		PagedResourcesAssembler<PassagemDTO> assembler = new PagedResourcesAssembler<>(resolver, null);
		PASSAGENS_PAGE_DTO = assembler.toModel(PASSAGENS_DTO);

	}

	@Test
	public void savePassagem_WithDataValid_ReturnsPassagemAndStatusCreated() throws Exception {
		when(service.save(PASSAGEM_DTO)).thenReturn(PASSAGEM_DTO);
		String content = mapper.writeValueAsString(PASSAGEM_DTO);
		String uri = "/api/passagens";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$").value(PASSAGEM_DTO));
	}

	@Test
	public void savePassagem_WithDataInvalid_ReturnsBadRequest() throws Exception {
		String content = mapper.writeValueAsString(INVALID_PASSAGEM_DTO);
		String uri = "/api/passagens";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updatePassagem_WithDataValid_ReturnsPassagemAndStatusOk() throws Exception {
		when(service.findById(1)).thenReturn(PASSAGEM_DTO);
		when(service.update(PASSAGEM_DTO, 1)).thenReturn(PASSAGEM_DTO);
		String content = mapper.writeValueAsString(PASSAGEM_DTO);
		String uri = "/api/passagens/1";
		mockMvc.perform(put(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(PASSAGEM_DTO));
	}

	@Test
	public void findPassagem_ById_ReturnsPassagemAndStatusOK() throws Exception {
		when(service.findById(1)).thenReturn(PASSAGEM_DTO);
		String content = mapper.writeValueAsString(PASSAGEM_DTO);
		String uri = "/api/passagens/1";

		mockMvc.perform(get(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(PASSAGEM_DTO));

	}

	@Test
	public void findPassagem_ByUnexistingId_ReturnsNotFound() throws Exception {
		when(service.findById(1)).thenThrow(ResourceNotFoundException.class);
		String uri = "/api/passagens/1";

		mockMvc.perform(get(uri)).andExpect(status().isNotFound());

	}

	@Test
	public void findAllPassagens_ReturnsPagesPassagemAndStatusOk() throws Exception {
		String jsonData = "{\"_embedded\":{\"passagemDTOList\":[{\"id\":1,\"origem\":\"Origem\",\"destino\":\"Destino\",\"preco\":1000.0,\"desconto\":0.05,\"caminhoImagem\":\"CaminhoImagem\",\"tiposPassagem\":\"AVIAO\"},{\"id\":2,\"origem\":\"Origem\",\"destino\":\"Destino\",\"preco\":1000.0,\"desconto\":0.05,\"caminhoImagem\":\"CaminhoImagem\",\"tiposPassagem\":\"ONIBUS\"},{\"id\":3,\"origem\":\"Origem\",\"destino\":\"Destino\",\"preco\":1000.0,\"desconto\":0.05,\"caminhoImagem\":\"CaminhoImagem\",\"tiposPassagem\":\"CRUZEIRO\"}]}, \"_links\":{\"self\":{\"href\":\"http://localhost\"}}, \"page\":{\"size\":3, \"totalElements\":3, \"totalPages\":1,\"number\":0}}";

		Pageable pageable = PageRequest.of(0, 3);
		when(service.findAll(pageable)).thenReturn(PASSAGENS_PAGE_DTO);
		String content = mapper.writeValueAsString(PASSAGENS_PAGE_DTO);
		String uri = "/api/passagens";

		mockMvc.perform(get(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("*", hasSize(3)))
				.andExpect(content().json(jsonData));

	}

	@Test
	public void deletePassagem_ById_ReturnsNoContent() throws Exception {
		String uri = "/api/passagens/1";

		mockMvc.perform(delete(uri)).andExpect(status().isNoContent());

	}

}

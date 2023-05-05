package com.agencia.vousuave.integrationtests.pacote;

import static com.agencia.vousuave.mocks.MockPacote.INVALID_PACOTE_DTO;
import static com.agencia.vousuave.mocks.MockPacote.PACOTE_DTO;
import static com.agencia.vousuave.mocks.MockPacote.PACOTES_DTO;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.agencia.vousuave.controller.PacoteController;
import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.mocks.MockPacote;
import com.agencia.vousuave.security.jwt.AuthEntryPointJwt;
import com.agencia.vousuave.security.jwt.JwtUtils;
import com.agencia.vousuave.service.PacoteService;
import com.agencia.vousuave.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PacoteController.class)
public class PacoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private PacoteService service;

	@MockBean
	private UserDetailsServiceImpl impl;

	@MockBean
	private AuthEntryPointJwt unauthoriezedHandler;

	@MockBean
	private JwtUtils jwtUtils;

	@MockBean
	private MockPacote mockPacote;

	private static PagedModel<EntityModel<PacoteDTO>> PACOTES_PAGE_DTO;

	@BeforeAll
	public static void setup() {
		HttpServletRequest mockRequest = new MockHttpServletRequest();
		ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
		RequestContextHolder.setRequestAttributes(servletRequestAttributes);
		HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();
		PagedResourcesAssembler<PacoteDTO> assembler = new PagedResourcesAssembler<>(resolver, null);
		PACOTES_PAGE_DTO = assembler.toModel(PACOTES_DTO);

	}

	@Test
	public void savePacote_WithDataValid_ReturnsPacoteAndStatusCreated() throws Exception {
		when(service.save(PACOTE_DTO)).thenReturn(PACOTE_DTO);
		String content = mapper.writeValueAsString(PACOTE_DTO);
		String uri = "/api/pacotes";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$").value(PACOTE_DTO));
	}

	@Test
	public void savePacote_WithDataInvalid_ReturnsBadRequest() throws Exception {
		String content = mapper.writeValueAsString(INVALID_PACOTE_DTO);
		String uri = "/api/pacotes";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updatePacote_WithDataValid_ReturnsPacoteAndStatusOk() throws Exception {
		when(service.findById(1)).thenReturn(PACOTE_DTO);
		when(service.update(PACOTE_DTO, 1)).thenReturn(PACOTE_DTO);
		String content = mapper.writeValueAsString(PACOTE_DTO);
		String uri = "/api/pacotes/1";
		mockMvc.perform(put(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(PACOTE_DTO));
	}

	@Test
	public void findPacote_ById_ReturnsPacoteAndStatusOK() throws Exception {
		when(service.findById(1)).thenReturn(PACOTE_DTO);
		String content = mapper.writeValueAsString(PACOTE_DTO);
		String uri = "/api/pacotes/1";

		mockMvc.perform(get(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(PACOTE_DTO));

	}

	@Test
	public void findPacote_ByUnexistingId_ReturnsNotFound() throws Exception {
		when(service.findById(1)).thenThrow(ResourceNotFoundException.class);
		String uri = "/api/pacotes/1";

		mockMvc.perform(get(uri)).andExpect(status().isNotFound());

	}

	@Test
	public void findAllPacotes_ReturnsPagesPacoteAndStatusOk() throws Exception {
		String jsonData = "{\"_embedded\":{\"pacoteDTOList\":[{\"id\":1,\"destino\":\"Destino\",\"preco\":1000.0,\"desconto\":0.1,\"diarias\":1,\"hotel\":\"Hotel 1\",\"caminhoImagem\":\"CaminhoImagem\",\"guia\":false,\"internacional\":false},{\"id\":2,\"destino\":\"Destino\",\"preco\":2000.0,\"desconto\":0.2,\"diarias\":2,\"hotel\":\"Hotel 2\",\"caminhoImagem\":\"CaminhoImagem\",\"guia\":false,\"internacional\":false},{\"id\":3,\"destino\":\"Destino\",\"preco\":3000.0,\"desconto\":0.3,\"diarias\":3,\"hotel\":\"Hotel 3\",\"caminhoImagem\":\"CaminhoImagem\",\"guia\":false,\"internacional\":false}]},\"_links\":{\"self\":{\"href\":\"http://localhost\"}},\"page\":{\"size\":3,\"totalElements\":3,\"totalPages\":1,\"number\":0}}";

		Pageable pageable = PageRequest.of(0, 3);
		when(service.findAll(pageable)).thenReturn(PACOTES_PAGE_DTO);
		String content = mapper.writeValueAsString(PACOTES_PAGE_DTO);
		String uri = "/api/pacotes";
		

		mockMvc.perform(get(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("*", hasSize(3)))
		.andExpect(content().json(jsonData)).andExpect(status().isOk());
		
	}

	@Test
	public void deletePacote_ById_ReturnsNoContent() throws Exception {
		String uri = "/api/pacotes/1";

		mockMvc.perform(delete(uri)).andExpect(status().isNoContent());

	}

}

package com.agencia.vousuave.integrationtests.usuario;

import static com.agencia.vousuave.mocks.MockUsuario.INVALID_USUARIO_DTO;
import static com.agencia.vousuave.mocks.MockUsuario.USUARIOS_DTO;
import static com.agencia.vousuave.mocks.MockUsuario.USUARIO_DTO;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.agencia.vousuave.controller.UsuarioController;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.mocks.MockUsuario;
import com.agencia.vousuave.security.jwt.AuthEntryPointJwt;
import com.agencia.vousuave.security.jwt.JwtUtils;
import com.agencia.vousuave.service.UsuarioService;
import com.agencia.vousuave.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private UsuarioService service;

	@MockBean
	private UserDetailsServiceImpl impl;

	@MockBean
	private AuthEntryPointJwt unauthoriezedHandler;

	@MockBean
	private JwtUtils jwtUtils;

	@MockBean
	private MockUsuario mockUsuario;


	@Test
	public void saveUsuario_WithDataValid_ReturnsUsuarioAndStatusCreated() throws Exception {
		when(service.save(USUARIO_DTO)).thenReturn(USUARIO_DTO);
		String content = mapper.writeValueAsString(USUARIO_DTO);
		String uri = "/api/usuarios";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.nome").value(USUARIO_DTO.getNome()))
				.andExpect(jsonPath("$.cpf").value(USUARIO_DTO.getCpf()))
				.andExpect(jsonPath("$.celular").value(USUARIO_DTO.getCelular()))
				.andExpect(jsonPath("$.email").value(USUARIO_DTO.getEmail()));
	}

	@Test
	public void saveUsuario_WithDataInvalid_ReturnsBadRequest() throws Exception {
		String content = mapper.writeValueAsString(INVALID_USUARIO_DTO);
		String uri = "/api/usuarios";
		mockMvc.perform(post(uri).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateUsuario_WithDataValid_ReturnsUsuarioAndStatusOk() throws Exception {
		when(service.findById(1)).thenReturn(USUARIO_DTO);
		when(service.update(USUARIO_DTO, 1)).thenReturn(USUARIO_DTO);
		String content = mapper.writeValueAsString(USUARIO_DTO);
		String uri = "/api/usuarios/1";
		mockMvc.perform(put(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(USUARIO_DTO.getNome()))
				.andExpect(jsonPath("$.cpf").value(USUARIO_DTO.getCpf()))
				.andExpect(jsonPath("$.celular").value(USUARIO_DTO.getCelular()))
				.andExpect(jsonPath("$.email").value(USUARIO_DTO.getEmail()));
	}

	@Test
	public void findUsuario_ById_ReturnsUsuarioAndStatusOK() throws Exception {
		when(service.findById(1)).thenReturn(USUARIO_DTO);
		String content = mapper.writeValueAsString(USUARIO_DTO);
		String uri = "/api/usuarios/1";

		mockMvc.perform(get(uri).content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(USUARIO_DTO.getNome()))
				.andExpect(jsonPath("$.cpf").value(USUARIO_DTO.getCpf()))
				.andExpect(jsonPath("$.celular").value(USUARIO_DTO.getCelular()))
				.andExpect(jsonPath("$.email").value(USUARIO_DTO.getEmail()));

	}

	@Test
	public void findUsuario_ByUnexistingId_ReturnsNotFound() throws Exception {
		when(service.findById(1)).thenThrow(ResourceNotFoundException.class);
		String uri = "/api/usuarios/1";

		mockMvc.perform(get(uri)).andExpect(status().isNotFound());

	}

	@Test
	public void findAllUsuarios_ReturnsPagesUsuarioAndStatusOk() throws Exception {
		String jsonData = "[{\"nome\":\"Usuario 1\",\"email\":\"usuario1@email.com\",\"cpf\":\"88913862000\",\"celular\":\"21888888888\",\"dataNascimento\":\"2023-03-14T15:37:05.908+00:00\",\"senha\":\"654321\",\"status\":false,\"links\":[]},{\"nome\":\"Usuario 2\",\"email\":\"usuario2@email.com\",\"cpf\":\"57677415067\",\"celular\":\"21777777777\",\"dataNascimento\":\"2023-03-14T15:37:05.908+00:00\",\"senha\":\"987654\",\"status\":false,\"links\":[]},{\"nome\":\"Usuario 3\",\"email\":\"usuario3@email.com\",\"cpf\":\"99828656094\",\"celular\":\"21666666666\",\"dataNascimento\":\"2023-03-14T15:37:05.908+00:00\",\"senha\":\"000000\",\"status\":false,\"links\":[]}]";

		when(service.findAll()).thenReturn(USUARIOS_DTO);
		String content = mapper.writeValueAsString(USUARIOS_DTO);
		String uri = "/api/usuarios";

		mockMvc.perform(get(uri).content(content).contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("*", hasSize(3)))
		.andExpect(status().isOk());


	}

	@Test
	public void deleteUsuario_ById_ReturnsNoContent() throws Exception {
		String uri = "/api/usuarios/1";

		mockMvc.perform(delete(uri)).andExpect(status().isNoContent());

	}

}

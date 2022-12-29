package com.agencia.vousuave.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.vousuave.dto.LoginDTO;
import com.agencia.vousuave.dto.UsuarioDTO;
import com.agencia.vousuave.response.JwtResponse;
import com.agencia.vousuave.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints for authentication")
@RequestMapping("/api/auth")
@RestController
public class AuthController {

	private final UsuarioService userSerice;

	@Operation(summary = "Authenticate Usuario", description = "Authenticate Usuario with username and password", tags = {
			"Usuario" }, responses = { @ApiResponse(responseCode = "200", content = @Content, description = "Success"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {

		JwtResponse jwtResponse = userSerice.authLogin(loginDTO);

		return ResponseEntity.ok(jwtResponse);
	}

	@Operation(summary = "Adds a new Usuario", description = "Adds a new Usuario", tags = { "Usuario" }, responses = {
			@ApiResponse(responseCode = "200", content = @Content, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioDTO usuarioDTO) {

		userSerice.save(usuarioDTO);

		return ResponseEntity.ok("User registered successfully!");
	}

}
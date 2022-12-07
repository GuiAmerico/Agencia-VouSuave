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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UsuarioService userSerice;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {

		JwtResponse jwtResponse = userSerice.authLogin(loginDTO);

		return ResponseEntity.ok(jwtResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioDTO usuarioDTO) {

		userSerice.save(usuarioDTO);

		return ResponseEntity.ok("User registered successfully!");
	}

}
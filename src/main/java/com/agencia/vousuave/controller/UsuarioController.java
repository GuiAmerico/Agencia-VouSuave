package com.agencia.vousuave.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.dto.UsuarioDTO;
import com.agencia.vousuave.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Endpoints for managing usuarios")
@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {

	private final UsuarioService service;


	@Operation(summary = "Finds all Usuario", description = "Finds all Usuario", tags = { "Usuario" }, responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class))) }, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());

	}

	@Operation(summary = "Finds a Usuario", description = "Finds a Usuario by Id", tags = { "Usuario" }, responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PassagemDTO.class)) }, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {

		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	@Operation(summary = "Adds a new Usuario", description = "Adds a new Usuario", tags = { "Usuario" }, responses = {
			@ApiResponse(responseCode = "200", content = @Content, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PostMapping
	public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioDTO usuarioDTO) throws ParseException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDTO));
	}

	@Operation(summary = "Updates a Usuario", description = "Updated", tags = { "Usuario" }, responses = {
			@ApiResponse(responseCode = "200", content = @Content, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(usuarioDTO, id));
	}

	@Operation(summary = "Deletes a Usuario", description = "Deleted Usuario by Id, updated status for false", tags = {
			"Usuario" }, responses = {
					@ApiResponse(responseCode = "204", content = @Content, description = "No Content"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario deletado com sucesso");
	}
}

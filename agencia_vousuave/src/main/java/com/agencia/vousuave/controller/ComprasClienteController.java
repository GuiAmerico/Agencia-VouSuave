package com.agencia.vousuave.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.vousuave.dto.ComprasClienteDTO;
import com.agencia.vousuave.service.ComprasClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "ComprasCliente", description = "Endpoints for managing compras")
@RequestMapping("/api/compras")
@RestController
public class ComprasClienteController {

	private final ComprasClienteService service;

	@Operation(summary = "Finds all Compras", description = "Finds all Compras, reference to Usuario from usuario_id ", tags = { "ComprasCliente",
			"Usuario" }, responses = { @ApiResponse(responseCode = "200", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = ComprasClienteDTO.class))) }, description = "Success"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@GetMapping("/{id}")
	public ResponseEntity<List<ComprasClienteDTO>> findAllCompras(@PathVariable(name = "id") Integer usuario_id) {

		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(usuario_id));

	}

	@Operation(summary = "Adds a new Compra", description = "Adds a new Compra, reference to Usuario from usuario_id ", tags = { "ComprasCliente",
			"Usuario" }, responses = { @ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ComprasClienteDTO.class)) }, description = "Success"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PostMapping("/{id}")
	public ResponseEntity<ComprasClienteDTO> save(@PathVariable Integer id,
			@RequestBody @Valid ComprasClienteDTO compras) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(compras, id));
	}

	@Operation(summary = "Deletes a Compra", description = "Deleted Compra by Id", tags = { "ComprasCliente",
			"Usuario" }, responses = {
					@ApiResponse(responseCode = "204", content = @Content, description = "No Content"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Compra cancelada com sucesso");
	}
}

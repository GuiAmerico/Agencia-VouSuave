package com.agencia.vousuave.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.service.PacoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "Pacote", description = "Endpoints for managing pacotes")
@RestController
@RequestMapping("/api/pacotes")
public class PacoteController {

	private final PacoteService service;

	@Operation(summary = "Finds all Pacotes", description = "Finds all Pacotes, with pagination,the size is 6", tags = {
			"Pacote" }, responses = { @ApiResponse(responseCode = "200", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = PacoteDTO.class))) }, description = "Success"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@GetMapping
	public ResponseEntity<PagedModel<EntityModel<PacoteDTO>>> findAll(@PageableDefault(size = 6, page = 0) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));

	}

	@Operation(summary = "Finds a Pacote", description = "Finds Pacote by Id", tags = { "Pacote" }, responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PassagemDTO.class)) }, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@GetMapping("/{id}")
	public ResponseEntity<PacoteDTO> findById(@PathVariable Integer id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	@Operation(summary = "Adds a new Pacote", description = "Adds a new Pacote", tags = { "Pacote" }, responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PacoteDTO.class)) }, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PostMapping
	public ResponseEntity<PacoteDTO> save(@RequestBody @Valid PacoteDTO pacoteDTO) throws ParseException {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pacoteDTO));
	}

	@Operation(summary = "Updates a Pacote", description = "Updated", tags = { "Pacote" }, responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PacoteDTO.class)) }, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PutMapping("/{id}")
	public ResponseEntity<PacoteDTO> update(@PathVariable Integer id, @RequestBody @Valid PacoteDTO pacoteDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(pacoteDTO, id));

	}

	@Operation(summary = "Deletes a Pacote", description = "Deleted Pacote by Id", tags = {
			"Pacote" }, responses = {
					@ApiResponse(responseCode = "204", content = @Content, description = "No Content"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pacote deletado com sucesso!");
	}

	
}

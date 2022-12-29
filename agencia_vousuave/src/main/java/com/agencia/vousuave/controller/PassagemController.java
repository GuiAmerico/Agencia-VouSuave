package com.agencia.vousuave.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.agencia.vousuave.service.PassagemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Tag(name = "Passagem", description = "Endpoints for managing passagens")
@RestController
@RequestMapping("/api/passagens")
public class PassagemController {

	private final PassagemService service;

	@Operation(summary = "Finds all Passagens", description = "Finds all Passagens, with pagination,the size is 3", tags = {
			"Passagem" }, responses = { @ApiResponse(responseCode = "200", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = PassagemDTO.class))) }, description = "Success"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@GetMapping
	public Page<PassagemDTO> findAll(@PageableDefault(size = 3, page = 0) Pageable pageable) {
		return service.findAll(pageable);

	}

	@Operation(summary = "Adds a new Passagem", description = "Adds a new Passagem", tags = {
			"Passagem" }, responses = {
					@ApiResponse(responseCode = "200", content = {
							@Content(schema = @Schema(implementation = PassagemDTO.class)) }, description = "Success"),
					@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
					@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
					@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PostMapping
	public ResponseEntity<PassagemDTO> save(@RequestBody @Valid PassagemDTO passagemDTO) throws ParseException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(passagemDTO));
	}

	@Operation(summary = "Updates a Passagem", description = "Updated", tags = { "Passagem" }, responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PassagemDTO.class)) }, description = "Success"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@PutMapping("/{id}")
	public ResponseEntity<PassagemDTO> update(@PathVariable Integer id, @RequestBody @Valid PassagemDTO passagemDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(passagemDTO, id));

	}

	@Operation(summary = "Deletes a Passagem", description = "Deleted passagem by Id", tags = { "Passagem" }, responses = {
			@ApiResponse(responseCode = "204", content = @Content, description = "No Content"),
			@ApiResponse(responseCode = "400", content = @Content, description = "Bad Request"),
			@ApiResponse(responseCode = "401", content = @Content, description = "Unauthorized"),
			@ApiResponse(responseCode = "404", content = @Content, description = "Not Found"),
			@ApiResponse(responseCode = "500", content = @Content, description = "Internal Error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Passagem deletada com sucesso!");
	}
}

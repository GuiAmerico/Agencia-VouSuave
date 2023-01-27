package com.agencia.vousuave.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.controller.PacoteController;
import com.agencia.vousuave.dto.PacoteDTO;
import com.agencia.vousuave.entity.Pacote;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.PacoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacoteService {

	private final PacoteRepository repository;

	private final PagedResourcesAssembler<PacoteDTO> assembler;

	public PacoteDTO save(PacoteDTO pacoteDTO) {
		Pacote pacote = new Pacote();
		BeanUtils.copyProperties(pacoteDTO, pacote);

		repository.save(pacote);
		BeanUtils.copyProperties(pacote, pacoteDTO);
		pacoteDTO.add(linkTo(methodOn(PacoteController.class).findById(pacoteDTO.getId())).withSelfRel());

		return pacoteDTO;
	}

	public PacoteDTO update(PacoteDTO pacoteDTO, Integer id) {
		Pacote pacote = new Pacote();
		existsById(id);
		pacoteDTO.setId(id);
		BeanUtils.copyProperties(pacoteDTO, pacote);
		pacoteDTO.add(linkTo(methodOn(PacoteController.class).findById(id)).withSelfRel());
		repository.save(pacote);
		return pacoteDTO;

	}

	public PagedModel<EntityModel<PacoteDTO>> findAll(Pageable pageable) {
		Page<Pacote> pacotes = repository.findAll(pageable);

		Page<PacoteDTO> pacotesDTO = pacotes.map(pacote -> new PacoteDTO(pacote));

		Link link = linkTo(methodOn(PacoteController.class).findAll(pageable)).withSelfRel();

		return assembler.toModel(pacotesDTO, link);

	}

	public PacoteDTO findById(Integer id) {
		PacoteDTO pacoteDTO = new PacoteDTO();
		Pacote pacote = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado"));
		BeanUtils.copyProperties(pacote, pacoteDTO);
		pacoteDTO.add(linkTo(methodOn(PacoteController.class).findById(id)).withSelfRel());
		return pacoteDTO;
	}

	public void deleteById(Integer id) {
		existsById(id);
		repository.deleteById(id);
	}

	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado"));
	}

}

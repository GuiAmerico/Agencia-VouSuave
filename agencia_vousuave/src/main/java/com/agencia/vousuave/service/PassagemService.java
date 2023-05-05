package com.agencia.vousuave.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.controller.PassagemController;
import com.agencia.vousuave.dto.PassagemDTO;
import com.agencia.vousuave.entity.Passagem;
import com.agencia.vousuave.exception.ResourceNotFoundException;
import com.agencia.vousuave.repository.PassagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PassagemService {

	private HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();
	private final PassagemRepository repository;
	private PagedResourcesAssembler<PassagemDTO> assembler = new PagedResourcesAssembler<>(resolver, null);

	public PassagemDTO save(PassagemDTO passagemDTO) {

		Passagem passagem = new Passagem();
		BeanUtils.copyProperties(passagemDTO, passagem);
		repository.save(passagem);
		BeanUtils.copyProperties(passagem, passagemDTO);
		passagemDTO.add(linkTo(methodOn(PassagemController.class).findById(passagemDTO.getId())).withSelfRel());
		return passagemDTO;
	}

	public PassagemDTO update(PassagemDTO passagemDTO, Integer id) {
		existsById(id);
		passagemDTO.setId(id);
		Passagem passagem = new Passagem();
		BeanUtils.copyProperties(passagemDTO, passagem);
		repository.save(passagem);
		BeanUtils.copyProperties(passagem, passagemDTO);
		passagemDTO.add(linkTo(methodOn(PassagemController.class).findById(passagemDTO.getId())).withSelfRel());

		return passagemDTO;

	}

	public PagedModel<EntityModel<PassagemDTO>> findAll(Pageable pageable) {
		Page<Passagem> passagens = repository.findAll(pageable);
		Page<PassagemDTO> passagensDTO = passagens.map(passagem -> new PassagemDTO(passagem));
		Link link = (linkTo(methodOn(PassagemController.class).findAll(pageable)).withSelfRel());
		return assembler.toModel(passagensDTO, link);
	}

	public PassagemDTO findById(Integer id) {
		PassagemDTO passagemDTO = new PassagemDTO();
		Passagem passagem = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Passagem não encontrada"));
		BeanUtils.copyProperties(passagem, passagemDTO);
		passagemDTO.add(linkTo(methodOn(PassagemController.class).findById(id)).withSelfRel());
		return passagemDTO;
	}

	public void deleteById(Integer id) {
		existsById(id);

		repository.deleteById(id);
	}

	private void existsById(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passagem não encontrada"));
	}

}

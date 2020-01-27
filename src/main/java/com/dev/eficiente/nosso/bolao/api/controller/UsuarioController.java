package com.dev.eficiente.nosso.bolao.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.eficiente.nosso.bolao.api.ResourceUriHelper;
import com.dev.eficiente.nosso.bolao.api.assembler.RepresentationModelAssemblerAndDisassembler;
import com.dev.eficiente.nosso.bolao.api.model.UsuarioModel;
import com.dev.eficiente.nosso.bolao.api.model.input.UsuarioInput;
import com.dev.eficiente.nosso.bolao.domain.model.Usuario;
import com.dev.eficiente.nosso.bolao.domain.repository.UsuarioRepository;
import com.dev.eficiente.nosso.bolao.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final UsuarioRepository usuarioRepository;
	private final RepresentationModelAssemblerAndDisassembler assemblerAndDisassembler;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository,
			RepresentationModelAssemblerAndDisassembler assemblerAndDisassembler) {
		this.usuarioService = usuarioService;
		this.usuarioRepository = usuarioRepository;
		this.assemblerAndDisassembler = assemblerAndDisassembler;
	}

	@GetMapping
	public List<UsuarioModel> listar() {
		return assemblerAndDisassembler
				.toCollectionRepresentationModel(UsuarioModel.class, usuarioRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = assemblerAndDisassembler
				.toDomainObject(Usuario.class, usuarioInput);
		
		UsuarioModel usuarioModel = assemblerAndDisassembler
				.toRepresentationModel(UsuarioModel.class, usuarioService.salvar(usuario));

		ResourceUriHelper.adicionaUriNoHeaderResponse(usuarioModel.getId());
		
		return usuarioModel;
	}
}

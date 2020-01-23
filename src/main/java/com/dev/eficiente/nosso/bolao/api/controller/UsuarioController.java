package com.dev.eficiente.nosso.bolao.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.eficiente.nosso.bolao.api.assembler.RepresentationModelAssemblerAndDisassembler;
import com.dev.eficiente.nosso.bolao.api.model.UsuarioModel;
import com.dev.eficiente.nosso.bolao.api.model.input.UsuarioInput;
import com.dev.eficiente.nosso.bolao.domain.model.Usuario;
import com.dev.eficiente.nosso.bolao.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final RepresentationModelAssemblerAndDisassembler assemblerAndDisassembler;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, 
			RepresentationModelAssemblerAndDisassembler assemblerAndDisassembler) {
		this.usuarioService = usuarioService;
		this.assemblerAndDisassembler = assemblerAndDisassembler;
	}

	@PostMapping
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = assemblerAndDisassembler
				.toRepresentationModel(Usuario.class, usuarioInput);
		
		return assemblerAndDisassembler
				.toRepresentationModel(UsuarioModel.class, usuarioService.salvar(usuario));
	}
}

package com.dev.eficiente.nosso.bolao.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dev.eficiente.nosso.bolao.api.model.input.UsuarioInput;
import com.dev.eficiente.nosso.bolao.domain.exception.NegocioException;
import com.dev.eficiente.nosso.bolao.domain.model.Usuario;
import com.dev.eficiente.nosso.bolao.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public void adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = 
				Usuario.criaUsuarioComLoginESenhaSemHash(usuarioInput.getLogin(), usuarioInput.getSenha());
		
		try {
			usuarioService.salvar(usuario);
		} catch (NegocioException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
}

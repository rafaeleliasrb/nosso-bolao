package com.dev.eficiente.nosso.bolao.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.eficiente.nosso.bolao.domain.exception.NegocioException;
import com.dev.eficiente.nosso.bolao.domain.model.Usuario;
import com.dev.eficiente.nosso.bolao.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	public void salvar(Usuario usuario) {
		verificaLoginjaExistente(usuario);
		
		usuarioRepository.save(usuario);
	}

	private void verificaLoginjaExistente(Usuario usuario) {
		if(usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
			throw new NegocioException("O login já está em uso por outro usuário");
		}
	}
}

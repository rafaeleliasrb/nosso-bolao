package com.dev.eficiente.nosso.bolao.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.eficiente.nosso.bolao.domain.exception.NegocioException;
import com.dev.eficiente.nosso.bolao.domain.model.Usuario;
import com.dev.eficiente.nosso.bolao.domain.repository.UsuarioRepository;
import com.dev.eficiente.nosso.bolao.infrastructure.security.CriptografiaUtil;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final CriptografiaUtil criptografiaUtil;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, CriptografiaUtil criptografiaUtil) {
		this.usuarioRepository = usuarioRepository;
		this.criptografiaUtil = criptografiaUtil;
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		if(usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
			throw new NegocioException("Esse login já está em uso por outro usuário");
		}
		
		usuario.setSenha(criptografiaUtil.criptografar(usuario.getSenha()));
		
		return usuarioRepository.save(usuario);
	}
}

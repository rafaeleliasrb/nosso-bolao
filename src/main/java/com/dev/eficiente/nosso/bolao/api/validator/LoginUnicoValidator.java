package com.dev.eficiente.nosso.bolao.api.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.eficiente.nosso.bolao.api.model.input.UsuarioInput;
import com.dev.eficiente.nosso.bolao.domain.repository.UsuarioRepository;

public class LoginUnicoValidator implements Validator {

	private UsuarioRepository usuarioRepository;

	public LoginUnicoValidator(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioInput.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UsuarioInput usuarioInput = (UsuarioInput) target;
		if(usuarioRepository.findByLogin(usuarioInput.getLogin()).isPresent()) {
			errors.rejectValue("login", "", 
					"Já existe um usuário com o login: " + usuarioInput.getLogin());
		}

	}

}

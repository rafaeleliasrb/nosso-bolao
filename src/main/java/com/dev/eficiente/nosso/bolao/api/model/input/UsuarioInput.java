package com.dev.eficiente.nosso.bolao.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	private String login;
	
	@NotBlank
	@Email
	private String password;
}

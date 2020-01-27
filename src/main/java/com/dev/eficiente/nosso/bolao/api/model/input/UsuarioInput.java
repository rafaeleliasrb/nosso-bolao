package com.dev.eficiente.nosso.bolao.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	@Length(min = 6, max = 255)
	private String senha;
}

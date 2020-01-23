package com.dev.eficiente.nosso.bolao.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	private Long id;
	
	private String login;
	
	private String password;
}

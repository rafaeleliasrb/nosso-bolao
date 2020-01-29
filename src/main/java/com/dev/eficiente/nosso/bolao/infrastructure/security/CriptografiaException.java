package com.dev.eficiente.nosso.bolao.infrastructure.security;

public class CriptografiaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CriptografiaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public CriptografiaException(String mensagem) {
		super(mensagem);
	}
}

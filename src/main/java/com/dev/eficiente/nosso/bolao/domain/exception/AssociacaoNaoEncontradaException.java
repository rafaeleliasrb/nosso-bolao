package com.dev.eficiente.nosso.bolao.domain.exception;

public class AssociacaoNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AssociacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}

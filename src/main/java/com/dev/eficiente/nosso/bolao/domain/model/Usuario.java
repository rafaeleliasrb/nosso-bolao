package com.dev.eficiente.nosso.bolao.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.dev.eficiente.nosso.bolao.infrastructure.security.CriptografiaUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataCriacao;
	
	private Usuario(String login, String senhaSemHash) {
		this.login = login;
		this.senha = CriptografiaUtil.criptografar(senhaSemHash);
	}
	
	public static Usuario criaUsuarioComLoginESenhaSemHash(String login, String senhaSemHash) {
		return new Usuario(login, senhaSemHash);
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}
}

package com.dev.eficiente.nosso.bolao.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Campeonato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Column(nullable = false)
	private OffsetDateTime dataInicio;
	
	@Column(nullable = false)
	private Integer quantidadeTimes;
	
	@Deprecated
	public Campeonato() {}

	public Campeonato(String nome, OffsetDateTime dataInicio, Integer quantidadeTimes) {
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.quantidadeTimes = quantidadeTimes;
	}
}

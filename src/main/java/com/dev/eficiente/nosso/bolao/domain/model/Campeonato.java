package com.dev.eficiente.nosso.bolao.domain.model;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	
	@ManyToMany
	@JoinTable(name = "time_campeonato",
			joinColumns = @JoinColumn(name = "campeonato_id"),
			inverseJoinColumns = @JoinColumn(name = "time_id"))
	private Set<Time> times = new HashSet<>();
	
	@Deprecated
	public Campeonato() {}

	public Campeonato(String nome, OffsetDateTime dataInicio, Integer quantidadeTimes) {
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.quantidadeTimes = quantidadeTimes;
	}
	
	public Set<Time> getTimes() {
		return Collections.unmodifiableSet(times);
	}
	
	public void adicionaTime(Time novoTime) {
		times.add(novoTime);
	}
}

package com.dev.eficiente.nosso.bolao.api.model.input;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import com.dev.eficiente.nosso.bolao.domain.model.Campeonato;
import com.dev.eficiente.nosso.bolao.domain.model.Time;
import com.dev.eficiente.nosso.bolao.domain.repository.TimeRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampeonatoInput {

	@NotBlank
	private String nome;
	
	@FutureOrPresent
	private OffsetDateTime dataInicio;
	
	private Integer quantidadeTimes;
	
	@Valid
	private Set<TimeIdInput> times = new HashSet<>();
	
	public boolean numeroDeTimesAdicionadosDiferenteDaQuantidadeTimes() {
		return temTimes() && times.size() != quantidadeTimes;
	}
	
	public Campeonato novoCampeonato(TimeRepository timeRepository) {
		Campeonato novoCampeonato = new Campeonato(nome, dataInicio, quantidadeTimes);
		if(temTimes()) {
			adicionarTimesAoCampeonato(timeRepository, novoCampeonato);
		}
		
		return novoCampeonato;
	}

	private void adicionarTimesAoCampeonato(TimeRepository timeRepository, Campeonato novoCampeonato) {
		times.stream().forEach(timeIdInput -> {
			Optional<Time> time = timeRepository.findById(timeIdInput.getId());
			novoCampeonato.adicionaTime(time.get());
		});
	}
	
	private boolean temTimes() {
		return !times.isEmpty();
	}
}

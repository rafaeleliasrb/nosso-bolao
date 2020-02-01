package com.dev.eficiente.nosso.bolao.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dev.eficiente.nosso.bolao.api.model.input.CampeonatoInput;
import com.dev.eficiente.nosso.bolao.api.validator.NomeCampeonatoUnicoValidator;
import com.dev.eficiente.nosso.bolao.api.validator.NumeroTimesAdicionadoIgualQuantidadeTimesDoCampeonatoValidator;
import com.dev.eficiente.nosso.bolao.domain.exception.AssociacaoNaoEncontradaException;
import com.dev.eficiente.nosso.bolao.domain.model.Campeonato;
import com.dev.eficiente.nosso.bolao.domain.model.Time;
import com.dev.eficiente.nosso.bolao.domain.repository.CampeonatoRepository;
import com.dev.eficiente.nosso.bolao.domain.repository.TimeRepository;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	private final CampeonatoRepository campeonatoRepository;
	private final TimeRepository timeRepository;

	@Autowired
	public CampeonatoController(CampeonatoRepository campeonatoRepository, TimeRepository timeRepository) {
		this.campeonatoRepository = campeonatoRepository;
		this.timeRepository = timeRepository;
	}
	
	@InitBinder("campeonatoInput")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new NomeCampeonatoUnicoValidator(campeonatoRepository));
        binder.addValidators(new NumeroTimesAdicionadoIgualQuantidadeTimesDoCampeonatoValidator());
    } 
	
	@PostMapping
	public void adicionar(@RequestBody @Valid CampeonatoInput campeonatoInput) {
		try {
			Campeonato novoCampeonato = new Campeonato(
					campeonatoInput.getNome(), campeonatoInput.getDataInicio(), campeonatoInput.getQuantidadeTimes());
			
			adicionaTimesAoCampeonato(campeonatoInput, novoCampeonato);
			
			campeonatoRepository.save(novoCampeonato);
			
		} catch (AssociacaoNaoEncontradaException ex) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, ex.getMessage() + " não encontrado", ex);
		}
	}

	private void adicionaTimesAoCampeonato(CampeonatoInput campeonatoInput, Campeonato campeonato) {
		if(campeonatoInput.getTimes().isPresent()) {
			campeonatoInput.getTimes().get().stream().forEach(timeIdInput -> {
					Optional<Time> time = timeRepository.findById(timeIdInput.getId());
					if(time.isPresent()) {
						campeonato.adicionaTime(time.get());
					}
					else {
						throw new AssociacaoNaoEncontradaException("Time de id: " + timeIdInput.getId() + " não encontrado");
					}
				});
		}
	}
	
}

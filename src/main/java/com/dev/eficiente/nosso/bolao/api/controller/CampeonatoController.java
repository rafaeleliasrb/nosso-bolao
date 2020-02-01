package com.dev.eficiente.nosso.bolao.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.eficiente.nosso.bolao.api.model.input.CampeonatoInput;
import com.dev.eficiente.nosso.bolao.api.validator.IdTimeExistenteValidator;
import com.dev.eficiente.nosso.bolao.api.validator.NomeCampeonatoUnicoValidator;
import com.dev.eficiente.nosso.bolao.api.validator.NumeroTimesAdicionadoIgualQuantidadeTimesDoCampeonatoValidator;
import com.dev.eficiente.nosso.bolao.domain.model.Campeonato;
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
        binder.addValidators(new IdTimeExistenteValidator(timeRepository));
    } 
	
	@PostMapping
	public void adicionar(@RequestBody @Valid CampeonatoInput campeonatoInput) {
		Campeonato novoCampeonato = campeonatoInput.novoCampeonato(timeRepository);
			
		campeonatoRepository.save(novoCampeonato);
	}
	
}

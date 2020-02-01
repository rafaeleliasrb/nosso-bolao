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
import com.dev.eficiente.nosso.bolao.api.validator.NomeCampeonatoUnicoValidator;
import com.dev.eficiente.nosso.bolao.domain.model.Campeonato;
import com.dev.eficiente.nosso.bolao.domain.repository.CampeonatoRepository;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	private CampeonatoRepository campeonatoRepository;

	@Autowired
	public CampeonatoController(CampeonatoRepository campeonatoRepository) {
		this.campeonatoRepository = campeonatoRepository;
	}
	
	@InitBinder("campeonatoInput")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new NomeCampeonatoUnicoValidator(campeonatoRepository));
    } 
	
	@PostMapping
	public void adicionar(@RequestBody @Valid CampeonatoInput campeonatoInput) {
		Campeonato novoCampeonato = new Campeonato(
				campeonatoInput.getNome(), campeonatoInput.getDataInicio(), campeonatoInput.getQuantidadeTimes());
		
		campeonatoRepository.save(novoCampeonato);
	}
}

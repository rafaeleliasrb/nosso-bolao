package com.dev.eficiente.nosso.bolao.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.eficiente.nosso.bolao.api.model.input.TimeInput;
import com.dev.eficiente.nosso.bolao.api.validator.NomeTimeUnicoValidator;
import com.dev.eficiente.nosso.bolao.domain.model.Time;
import com.dev.eficiente.nosso.bolao.domain.repository.TimeRepository;

@RestController
@RequestMapping("/times")
public class TimeController {

	private TimeRepository timeRepository;

	@Autowired
	public TimeController(TimeRepository timeRepository) {
		this.timeRepository = timeRepository;
	}
	
	@InitBinder("timeInput")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(new NomeTimeUnicoValidator(timeRepository));
    } 
	
	@PostMapping
	public void adicionar(@RequestBody @Valid TimeInput timeInput) {
		Time novoTime = new Time(timeInput.getNome(), timeInput.getDataFundacao());
		
		timeRepository.save(novoTime);
	}
}

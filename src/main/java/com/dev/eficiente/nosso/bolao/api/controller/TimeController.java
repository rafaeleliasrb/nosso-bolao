package com.dev.eficiente.nosso.bolao.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.eficiente.nosso.bolao.domain.repository.TimeRepository;

@RestController
@RequestMapping("/times")
public class TimeController {

	private TimeRepository timeRepository;

	@Autowired
	public TimeController(TimeRepository timeRepository) {
		this.timeRepository = timeRepository;
	}
	
	@PostMapping
	public void adicionar() {
		
	}
}

package com.dev.eficiente.nosso.bolao.api.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.eficiente.nosso.bolao.api.model.input.TimeInput;
import com.dev.eficiente.nosso.bolao.domain.repository.TimeRepository;

public class NomeTimeUnicoValidator implements Validator {

	private TimeRepository timeRepository;

	public NomeTimeUnicoValidator(TimeRepository timeRepository) {
		this.timeRepository = timeRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TimeInput.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TimeInput timeInput = (TimeInput) target;
		if(timeRepository.findByNome(timeInput.getNome()).isPresent()) {
			errors.rejectValue("nome", "", "Já existe um time com o nome: " + timeInput.getNome());
		}
	}

}

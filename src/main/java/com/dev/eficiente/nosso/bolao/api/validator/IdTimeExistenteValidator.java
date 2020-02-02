package com.dev.eficiente.nosso.bolao.api.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.eficiente.nosso.bolao.api.model.input.CampeonatoInput;
import com.dev.eficiente.nosso.bolao.api.model.input.TimeIdInput;
import com.dev.eficiente.nosso.bolao.domain.repository.TimeRepository;

public class IdTimeExistenteValidator implements Validator {

	private TimeRepository timeRepository;

	public IdTimeExistenteValidator(TimeRepository timeRepository) {
		this.timeRepository = timeRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CampeonatoInput.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CampeonatoInput campeonatoInput = (CampeonatoInput) target;
		try {
			campeonatoInput.getTimes().stream()
				.forEach(this::verificaSeTimeExisteCasoContrarioFalhar);
		} catch (IllegalArgumentException e) {
			errors.rejectValue("times", "", e.getMessage());
		}

	}

	private void verificaSeTimeExisteCasoContrarioFalhar(TimeIdInput timeIdInput) {
		if(timeRepository.findById(timeIdInput.getId()).isEmpty()) {
			throw new IllegalArgumentException("Time de id: " + timeIdInput.getId() + " não encontrado");
		}
	}

}

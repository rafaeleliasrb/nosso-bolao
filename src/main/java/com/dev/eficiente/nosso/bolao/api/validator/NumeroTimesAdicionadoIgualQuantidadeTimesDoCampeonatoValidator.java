package com.dev.eficiente.nosso.bolao.api.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.eficiente.nosso.bolao.api.model.input.CampeonatoInput;

public class NumeroTimesAdicionadoIgualQuantidadeTimesDoCampeonatoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CampeonatoInput.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CampeonatoInput campeonatoInput = (CampeonatoInput) target;
		if(campeonatoInput.numeroDeTimesAdicionadosDiferenteDaQuantidadeTimes()) {
			errors.rejectValue("quantidadeTimes", "", 
					"O número de times do campeoanto deve ser igual a: " + campeonatoInput.getQuantidadeTimes());
		}
	}

}

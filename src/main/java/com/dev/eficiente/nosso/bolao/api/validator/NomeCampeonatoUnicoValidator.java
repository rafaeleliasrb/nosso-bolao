package com.dev.eficiente.nosso.bolao.api.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.eficiente.nosso.bolao.api.model.input.CampeonatoInput;
import com.dev.eficiente.nosso.bolao.domain.repository.CampeonatoRepository;

public class NomeCampeonatoUnicoValidator implements Validator {

	private CampeonatoRepository campeonatoRepository;

	public NomeCampeonatoUnicoValidator(CampeonatoRepository campeonatoRepository) {
		this.campeonatoRepository = campeonatoRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CampeonatoInput.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CampeonatoInput campeonatoInput = (CampeonatoInput) target;
		if(campeonatoRepository.findByNome(campeonatoInput.getNome()).isPresent()) {
			errors.rejectValue("nome", "", "Já existe um campeonato com o nome: " + campeonatoInput.getNome());
		}
	}

}

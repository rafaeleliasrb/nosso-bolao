package com.dev.eficiente.nosso.bolao.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeInput {

	@NotBlank
	private String nome;
	
	@Past
	private OffsetDateTime dataFundacao;
}

package com.dev.eficiente.nosso.bolao.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampeonatoInput {

	@NotBlank
	private String nome;
	
	@FutureOrPresent
	private OffsetDateTime dataInicio;
	
	private Integer quantidadeTimes;
}

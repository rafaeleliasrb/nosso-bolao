package com.dev.eficiente.nosso.bolao.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TimeIdInput {

	@NotNull
	private Long id;
}

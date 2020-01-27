package com.dev.eficiente.nosso.bolao.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepresentationModelAssemblerAndDisassembler {

	private ModelMapper modelMapper;
	
	@Autowired
	public RepresentationModelAssemblerAndDisassembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public <T> T toRepresentationModel(Class<T> modelClass, Object domainObject) {
		return mapObjectToNewClass(domainObject, modelClass);
	}
	
	public <T> T toDomainObject(Class<T> domainObjectClass, Object model) {
		return mapObjectToNewClass(model, domainObjectClass);
	}
	
	public <T, E> List<T> toCollectionRepresentationModel(Class<T> clazz, Collection<E> objects) {
		return objects.stream()
				.map(domainModel -> toRepresentationModel(clazz, domainModel))
				.collect(Collectors.toList());
	}
	
	public <T, E> void copyProperties(T source, E target) {
		modelMapper.map(source, target);
	}
	
	private <T> T mapObjectToNewClass(Object domainObject, Class<T> modelClass) {
		return modelMapper.map(domainObject, modelClass);
	}
}

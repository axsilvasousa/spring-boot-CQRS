package com.axdesousa.beautique.utils;

import org.modelmapper.ModelMapper;

public class ConvertMapper<E,D>{
    private final ModelMapper modelMapper;
    private final Class<E> entityType;
    private final Class<D> dtoType;

    public ConvertMapper(Class<E> entityType, Class<D> dtoType) {
        this.modelMapper = new ModelMapper();
        this.entityType = entityType;
        this.dtoType = dtoType;
    }

    public E convertToEntity(D dto){
        return modelMapper.map(dto, entityType);
    }

    public D convertToDto(E entity){
        return modelMapper.map(entity, dtoType);
    }
}

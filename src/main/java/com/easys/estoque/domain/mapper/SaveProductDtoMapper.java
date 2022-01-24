package com.easys.estoque.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.easys.estoque.domain.dto.SaveProductDto;
import com.easys.estoque.usecase.request.SaveProductRequest;
import com.easys.estoque.usecase.request.UpdateProductRequest;

@Mapper
public interface SaveProductDtoMapper {

	SaveProductDtoMapper INSTANCE = Mappers.getMapper(SaveProductDtoMapper.class);

	SaveProductDto toDto(SaveProductRequest request);

	SaveProductDto toDto(UpdateProductRequest request);

}

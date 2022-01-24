package com.easys.estoque.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.easys.estoque.domain.dto.ImportProductDto;
import com.easys.estoque.usecase.request.ImportProductRequest;

@Mapper
public interface ImportProductDtoMapper {

	ImportProductDtoMapper INSTANCE = Mappers.getMapper(ImportProductDtoMapper.class);

	List<ImportProductDto> toDto(List<ImportProductRequest> request);

}

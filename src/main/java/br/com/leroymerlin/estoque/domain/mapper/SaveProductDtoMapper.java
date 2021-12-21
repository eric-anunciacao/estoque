package br.com.leroymerlin.estoque.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leroymerlin.estoque.domain.dto.SaveProductDto;
import br.com.leroymerlin.estoque.usecase.request.SaveProductRequest;

@Mapper
public interface SaveProductDtoMapper {

	SaveProductDtoMapper INSTANCE = Mappers.getMapper(SaveProductDtoMapper.class);

	SaveProductDto toDto(SaveProductRequest request);

}

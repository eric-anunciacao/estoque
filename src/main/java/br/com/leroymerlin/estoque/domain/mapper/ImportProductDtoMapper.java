package br.com.leroymerlin.estoque.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leroymerlin.estoque.domain.dto.ImportProductDto;
import br.com.leroymerlin.estoque.usecase.request.ImportProductRequest;

@Mapper
public interface ImportProductDtoMapper {

	ImportProductDtoMapper INSTANCE = Mappers.getMapper(ImportProductDtoMapper.class);

	List<ImportProductDto> toDto(List<ImportProductRequest> request);

}

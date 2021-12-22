package br.com.leroymerlin.estoque.usecase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leroymerlin.estoque.domain.entity.Product;
import br.com.leroymerlin.estoque.usecase.response.UpdateProductResponse;

@Mapper
public interface UpdateProductResponseMapper {

	UpdateProductResponseMapper INSTANCE = Mappers.getMapper(UpdateProductResponseMapper.class);

	UpdateProductResponse toResponse(Product product);

}

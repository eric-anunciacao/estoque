package com.easys.estoque.usecase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.easys.estoque.domain.entity.Product;
import com.easys.estoque.usecase.response.UpdateProductResponse;

@Mapper
public interface UpdateProductResponseMapper {

	UpdateProductResponseMapper INSTANCE = Mappers.getMapper(UpdateProductResponseMapper.class);

	UpdateProductResponse toResponse(Product product);

}

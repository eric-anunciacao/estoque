package com.easys.estoque.usecase.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.easys.estoque.domain.entity.Product;
import com.easys.estoque.usecase.response.ProductResponse;

@Mapper
public interface ProductResponseMapper {

	ProductResponseMapper INSTANCE = Mappers.getMapper(ProductResponseMapper.class);

	List<ProductResponse> toResponses(List<Product> products);

	ProductResponse toResponse(Product product);

}

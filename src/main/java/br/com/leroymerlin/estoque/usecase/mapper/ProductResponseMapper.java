package br.com.leroymerlin.estoque.usecase.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leroymerlin.estoque.domain.entity.Product;
import br.com.leroymerlin.estoque.usecase.response.ProductResponse;

@Mapper
public interface ProductResponseMapper {

	ProductResponseMapper INSTANCE = Mappers.getMapper(ProductResponseMapper.class);

	List<ProductResponse> toResponses(List<Product> products);

	ProductResponse toResponse(Product product);

}

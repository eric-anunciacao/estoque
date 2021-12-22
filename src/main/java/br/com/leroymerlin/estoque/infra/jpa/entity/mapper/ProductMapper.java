package br.com.leroymerlin.estoque.infra.jpa.entity.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leroymerlin.estoque.domain.entity.Product;
import br.com.leroymerlin.estoque.infra.jpa.entity.ProductJpa;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	List<Product> toProducts(Iterable<ProductJpa> products);

	Product toProduct(ProductJpa productJpa);

}

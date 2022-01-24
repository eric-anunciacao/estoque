package com.easys.estoque.infra.jpa.entity.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.easys.estoque.domain.entity.Product;
import com.easys.estoque.infra.jpa.entity.ProductJpa;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	List<Product> toProducts(Iterable<ProductJpa> products);

	Product toProduct(ProductJpa productJpa);

}

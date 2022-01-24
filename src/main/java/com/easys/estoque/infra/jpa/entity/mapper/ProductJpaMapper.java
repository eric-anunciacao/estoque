package com.easys.estoque.infra.jpa.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.easys.estoque.domain.dto.SaveProductDto;
import com.easys.estoque.infra.jpa.entity.ProductJpa;

@Mapper
public interface ProductJpaMapper {

	ProductJpaMapper INSTANCE = Mappers.getMapper(ProductJpaMapper.class);

	ProductJpa toJpa(SaveProductDto dto);

}

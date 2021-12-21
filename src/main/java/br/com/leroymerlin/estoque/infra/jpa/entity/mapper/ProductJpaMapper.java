package br.com.leroymerlin.estoque.infra.jpa.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leroymerlin.estoque.domain.dto.SaveProductDto;
import br.com.leroymerlin.estoque.infra.jpa.entity.ProductJpa;

@Mapper
public interface ProductJpaMapper {

	ProductJpaMapper INSTANCE = Mappers.getMapper(ProductJpaMapper.class);

	ProductJpa toJpa(SaveProductDto dto);

}

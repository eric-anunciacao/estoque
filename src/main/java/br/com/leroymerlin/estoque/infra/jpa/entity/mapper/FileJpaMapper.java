package br.com.leroymerlin.estoque.infra.jpa.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leroymerlin.estoque.domain.dto.SaveFileDto;
import br.com.leroymerlin.estoque.infra.jpa.entity.FileJpa;

@Mapper
public interface FileJpaMapper {

	FileJpaMapper INSTANCE = Mappers.getMapper(FileJpaMapper.class);

	FileJpa toJpa(SaveFileDto dto);

}

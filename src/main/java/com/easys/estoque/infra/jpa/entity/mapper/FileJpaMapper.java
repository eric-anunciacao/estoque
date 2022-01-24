package com.easys.estoque.infra.jpa.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.easys.estoque.domain.dto.SaveFileDto;
import com.easys.estoque.infra.jpa.entity.FileJpa;

@Mapper
public interface FileJpaMapper {

	FileJpaMapper INSTANCE = Mappers.getMapper(FileJpaMapper.class);

	FileJpa toJpa(SaveFileDto dto);

}

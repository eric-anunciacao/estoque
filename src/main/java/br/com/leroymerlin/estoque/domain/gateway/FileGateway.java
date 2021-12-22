package br.com.leroymerlin.estoque.domain.gateway;

import br.com.leroymerlin.estoque.domain.dto.SaveFileDto;

public interface FileGateway {

	void save(SaveFileDto dto);

}

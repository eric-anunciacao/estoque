package br.com.leroymerlin.estoque.domain.gateway;

import br.com.leroymerlin.estoque.domain.dto.SaveProductDto;

public interface ProductGateway {

	void save(SaveProductDto dto);

}

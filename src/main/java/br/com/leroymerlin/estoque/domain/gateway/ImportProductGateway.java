package br.com.leroymerlin.estoque.domain.gateway;

import java.util.List;

import br.com.leroymerlin.estoque.domain.dto.ImportProductDto;

public interface ImportProductGateway {

	void sendToQueue(Long fileId, List<ImportProductDto> products);

}

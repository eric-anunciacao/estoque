package com.easys.estoque.domain.gateway;

import java.util.List;

import com.easys.estoque.domain.dto.ImportProductDto;

public interface ImportProductGateway {

	void sendToQueue(Long fileId, List<ImportProductDto> products);

}

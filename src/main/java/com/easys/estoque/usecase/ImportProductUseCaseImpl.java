package com.easys.estoque.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.ImportProductGateway;
import com.easys.estoque.domain.mapper.ImportProductDtoMapper;
import com.easys.estoque.usecase.request.ImportProductRequest;

@Component
class ImportProductUseCaseImpl implements ImportProductUseCase {

	private final ImportProductGateway gateway;

	public ImportProductUseCaseImpl(ImportProductGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void execute(Long fileId, List<ImportProductRequest> request) {
		var productsDto = ImportProductDtoMapper.INSTANCE.toDto(request);
		gateway.sendToQueue(fileId, productsDto);
	}

}

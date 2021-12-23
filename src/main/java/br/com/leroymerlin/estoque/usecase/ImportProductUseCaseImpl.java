package br.com.leroymerlin.estoque.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.ImportProductGateway;
import br.com.leroymerlin.estoque.domain.mapper.ImportProductDtoMapper;
import br.com.leroymerlin.estoque.usecase.request.ImportProductRequest;

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

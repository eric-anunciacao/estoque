package br.com.leroymerlin.estoque.usecase;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.domain.mapper.SaveProductDtoMapper;
import br.com.leroymerlin.estoque.infra.config.exception.BusinessException;
import br.com.leroymerlin.estoque.usecase.request.SaveProductRequest;

@Component
class SaveProductUseCaseImpl implements SaveProductUseCase {

	private final ProductGateway gateway;

	public SaveProductUseCaseImpl(ProductGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void save(SaveProductRequest request) {
		validate(request);
		var dto = SaveProductDtoMapper.INSTANCE.toDto(request);
		gateway.save(dto);
	}

	private void validate(SaveProductRequest request) {
		if (!request.hasValidId()) {
			throw new BusinessException(String.format("Product %s has invalid ID", request.getName()));
		}
	}

}

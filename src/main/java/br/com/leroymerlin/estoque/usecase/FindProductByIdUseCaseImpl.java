package br.com.leroymerlin.estoque.usecase;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.infra.config.exception.NoContentException;
import br.com.leroymerlin.estoque.usecase.mapper.ProductResponseMapper;
import br.com.leroymerlin.estoque.usecase.response.ProductResponse;

@Component
class FindProductByIdUseCaseImpl implements FindProductByIdUseCase {

	private final ProductGateway gateway;

	public FindProductByIdUseCaseImpl(ProductGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public ProductResponse findById(Long id) {
		var optional = this.gateway.findById(id);

		if (optional.isPresent()) {
			return ProductResponseMapper.INSTANCE.toResponse(optional.get());
		} else {
			throw new NoContentException(String.format("Product with LM '%s' not found", id));
		}
	}

}

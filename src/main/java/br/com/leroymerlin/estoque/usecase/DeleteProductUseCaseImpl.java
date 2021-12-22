package br.com.leroymerlin.estoque.usecase;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.validator.ProductValidator;

@Component
class DeleteProductUseCaseImpl implements DeleteProductUseCase {

	private final ProductGateway gateway;
	private final ProductValidator validator;

	public DeleteProductUseCaseImpl(final ProductGateway gateway, final ProductValidator validator) {
		this.gateway = gateway;
		this.validator = validator;
	}

	@Override
	public void delete(Long id) {
		this.validator.validate(id);
		this.gateway.delete(id);
	}

}

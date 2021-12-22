package br.com.leroymerlin.estoque.validator;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.infra.config.exception.ResourceNotFoundException;

@Component
class ProductValidatorImpl implements ProductValidator {

	private final ProductGateway gateway;

	public ProductValidatorImpl(ProductGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void validate(Long id) {
		if (!this.gateway.exists(id)) {
			throw new ResourceNotFoundException(String.format("Product not found for this id :: %s", id));
		}
	}

}

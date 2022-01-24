package com.easys.estoque.validator;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.ProductGateway;
import com.easys.estoque.infra.config.exception.ResourceNotFoundException;

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

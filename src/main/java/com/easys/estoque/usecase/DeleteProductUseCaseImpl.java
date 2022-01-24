package com.easys.estoque.usecase;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.ProductGateway;
import com.easys.estoque.validator.ProductValidator;

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

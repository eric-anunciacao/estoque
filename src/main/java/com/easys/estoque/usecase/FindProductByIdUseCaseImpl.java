package com.easys.estoque.usecase;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.ProductGateway;
import com.easys.estoque.infra.config.exception.NoContentException;
import com.easys.estoque.usecase.mapper.ProductResponseMapper;
import com.easys.estoque.usecase.response.ProductResponse;

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

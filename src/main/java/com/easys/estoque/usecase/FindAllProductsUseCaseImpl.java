package com.easys.estoque.usecase;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.ProductGateway;
import com.easys.estoque.infra.config.exception.NoContentException;
import com.easys.estoque.usecase.mapper.ProductResponseMapper;
import com.easys.estoque.usecase.response.ProductResponse;

@Component
class FindAllProductsUseCaseImpl implements FindAllProductsUseCase {

	private final ProductGateway gateway;

	public FindAllProductsUseCaseImpl(final ProductGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public List<ProductResponse> findAll() {
		var products = this.gateway.findAll();

		if (CollectionUtils.isNotEmpty(products)) {
			return ProductResponseMapper.INSTANCE.toResponses(products);
		} else {
			throw new NoContentException("No products were found!");
		}
	}

}

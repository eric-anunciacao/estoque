package br.com.leroymerlin.estoque.usecase;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.infra.config.exception.NoContentException;
import br.com.leroymerlin.estoque.usecase.mapper.ProductResponseMapper;
import br.com.leroymerlin.estoque.usecase.response.ProductResponse;

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

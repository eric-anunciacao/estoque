package br.com.leroymerlin.estoque.usecase;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.dto.SaveProductDto;
import br.com.leroymerlin.estoque.domain.entity.Product;
import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.infra.config.exception.ResourceNotFoundException;
import br.com.leroymerlin.estoque.usecase.request.PartialUpdateProductRequest;

@Component
class PartialUpdateProductUseCaseImpl implements PartialUpdateProductUseCase {

	private final ProductGateway gateway;

	public PartialUpdateProductUseCaseImpl(ProductGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void partialUpdate(PartialUpdateProductRequest request) {
		var optional = this.gateway.findById(request.getLm());

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException(String.format("Product not found for this id :: %s", request.getLm()));
		}

		this.gateway.save(getDto(request, optional.get()));
	}

	private SaveProductDto getDto(PartialUpdateProductRequest request, Product product) {
		var name = product.getName();
		var freeShipping = product.isFreeShipping();
		var description = product.getDescription();
		var price = product.getPrice();
		var category = product.getCategory();

		if (StringUtils.isNotEmpty(request.getName())) {
			name = request.getName();
		}

		if (request.getFreeShipping() != null) {
			freeShipping = request.getFreeShipping().booleanValue();
		}

		if (StringUtils.isNotEmpty(request.getDescription())) {
			description = request.getDescription();
		}

		if (request.getPrice() != null) {
			price = request.getPrice();
		}

		if (StringUtils.isNotEmpty(request.getCategory())) {
			category = request.getCategory();
		}

		return new SaveProductDto(request.getLm(), name, freeShipping, description, price, category);
	}

}

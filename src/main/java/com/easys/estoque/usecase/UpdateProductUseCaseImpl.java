package com.easys.estoque.usecase;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.ProductGateway;
import com.easys.estoque.domain.mapper.SaveProductDtoMapper;
import com.easys.estoque.usecase.mapper.UpdateProductResponseMapper;
import com.easys.estoque.usecase.request.UpdateProductRequest;
import com.easys.estoque.usecase.response.UpdateProductResponse;
import com.easys.estoque.validator.ProductValidator;

@Component
class UpdateProductUseCaseImpl implements UpdateProductUseCase {

	private final ProductGateway gateway;
	private final ProductValidator validator;

	public UpdateProductUseCaseImpl(ProductGateway gateway, final ProductValidator validator) {
		this.gateway = gateway;
		this.validator = validator;
	}

	@Override
	public UpdateProductResponse update(UpdateProductRequest request) {
		this.validator.validate(request.getLm());
		var dto = SaveProductDtoMapper.INSTANCE.toDto(request);
		var product = this.gateway.save(dto);
		return UpdateProductResponseMapper.INSTANCE.toResponse(product);
	}

}

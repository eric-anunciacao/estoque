package br.com.leroymerlin.estoque.usecase;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.domain.mapper.SaveProductDtoMapper;
import br.com.leroymerlin.estoque.usecase.mapper.UpdateProductResponseMapper;
import br.com.leroymerlin.estoque.usecase.request.UpdateProductRequest;
import br.com.leroymerlin.estoque.usecase.response.UpdateProductResponse;
import br.com.leroymerlin.estoque.validator.ProductValidator;

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

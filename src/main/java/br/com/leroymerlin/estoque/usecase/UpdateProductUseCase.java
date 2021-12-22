package br.com.leroymerlin.estoque.usecase;

import br.com.leroymerlin.estoque.usecase.request.UpdateProductRequest;
import br.com.leroymerlin.estoque.usecase.response.UpdateProductResponse;

public interface UpdateProductUseCase {

	UpdateProductResponse update(UpdateProductRequest request);

}

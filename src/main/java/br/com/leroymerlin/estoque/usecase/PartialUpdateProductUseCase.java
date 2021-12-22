package br.com.leroymerlin.estoque.usecase;

import br.com.leroymerlin.estoque.usecase.request.PartialUpdateProductRequest;

public interface PartialUpdateProductUseCase {

	void partialUpdate(PartialUpdateProductRequest request);

}

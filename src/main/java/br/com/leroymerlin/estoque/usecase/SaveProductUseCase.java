package br.com.leroymerlin.estoque.usecase;

import br.com.leroymerlin.estoque.usecase.request.SaveProductRequest;

public interface SaveProductUseCase {

	void save(SaveProductRequest request);

}

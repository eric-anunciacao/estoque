package br.com.leroymerlin.estoque.usecase;

import br.com.leroymerlin.estoque.usecase.response.ProductResponse;

public interface FindProductByIdUseCase {

	ProductResponse findById(Long id);

}

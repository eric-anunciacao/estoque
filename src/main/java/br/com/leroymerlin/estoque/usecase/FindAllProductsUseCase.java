package br.com.leroymerlin.estoque.usecase;

import java.util.List;

import br.com.leroymerlin.estoque.usecase.response.ProductResponse;

public interface FindAllProductsUseCase {

	List<ProductResponse> findAll();

}

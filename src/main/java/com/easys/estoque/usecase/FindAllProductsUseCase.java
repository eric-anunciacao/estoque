package com.easys.estoque.usecase;

import java.util.List;

import com.easys.estoque.usecase.response.ProductResponse;

public interface FindAllProductsUseCase {

	List<ProductResponse> findAll();

}

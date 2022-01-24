package com.easys.estoque.usecase;

import com.easys.estoque.usecase.response.ProductResponse;

public interface FindProductByIdUseCase {

	ProductResponse findById(Long id);

}

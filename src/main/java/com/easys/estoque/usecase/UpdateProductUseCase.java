package com.easys.estoque.usecase;

import com.easys.estoque.usecase.request.UpdateProductRequest;
import com.easys.estoque.usecase.response.UpdateProductResponse;

public interface UpdateProductUseCase {

	UpdateProductResponse update(UpdateProductRequest request);

}

package com.easys.estoque.usecase;

import java.util.List;

import com.easys.estoque.usecase.request.ImportProductRequest;

public interface ImportProductUseCase {

	void execute(Long fileId, List<ImportProductRequest> request);

}

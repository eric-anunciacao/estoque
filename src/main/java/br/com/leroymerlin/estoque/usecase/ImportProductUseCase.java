package br.com.leroymerlin.estoque.usecase;

import java.util.List;

import br.com.leroymerlin.estoque.usecase.request.ImportProductRequest;

public interface ImportProductUseCase {

	void execute(List<ImportProductRequest> request);

}

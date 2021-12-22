package br.com.leroymerlin.estoque.usecase;

import br.com.leroymerlin.estoque.usecase.request.ImportFileRequest;

public interface RegisterFileImportUseCase {

	void register(ImportFileRequest fileRequest);

}

package br.com.leroymerlin.estoque.usecase;

import br.com.leroymerlin.estoque.usecase.request.ImportFileRequest;

public interface LogFileImportUseCase {

	Long log(ImportFileRequest request);

}

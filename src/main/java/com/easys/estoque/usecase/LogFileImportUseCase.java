package com.easys.estoque.usecase;

import com.easys.estoque.usecase.request.ImportFileRequest;

public interface LogFileImportUseCase {

	Long log(ImportFileRequest request);

}

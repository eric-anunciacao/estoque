package com.easys.estoque.usecase;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.dto.SaveFileDto;
import com.easys.estoque.domain.gateway.FileGateway;
import com.easys.estoque.usecase.request.ImportFileRequest;

@Component
class LogFileImportUseCaseImpl implements LogFileImportUseCase {

	private final FileGateway gateway;

	public LogFileImportUseCaseImpl(FileGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public Long log(ImportFileRequest request) {
		var dto = new SaveFileDto(request.getFilename(), request.getNumberOfRecords());
		return this.gateway.save(dto);
	}

}

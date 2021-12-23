package br.com.leroymerlin.estoque.usecase;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.dto.SaveFileDto;
import br.com.leroymerlin.estoque.domain.gateway.FileGateway;
import br.com.leroymerlin.estoque.usecase.request.ImportFileRequest;

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

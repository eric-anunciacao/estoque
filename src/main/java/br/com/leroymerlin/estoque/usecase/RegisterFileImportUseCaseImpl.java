package br.com.leroymerlin.estoque.usecase;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.dto.SaveFileDto;
import br.com.leroymerlin.estoque.domain.gateway.FileGateway;
import br.com.leroymerlin.estoque.usecase.request.ImportFileRequest;

@Component
class RegisterFileImportUseCaseImpl implements RegisterFileImportUseCase {

	private final FileGateway gateway;

	public RegisterFileImportUseCaseImpl(FileGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void register(ImportFileRequest request) {
		var dto = new SaveFileDto(request.getFilename(), request.getNumberOfRecords());
		this.gateway.save(dto);
	}

}

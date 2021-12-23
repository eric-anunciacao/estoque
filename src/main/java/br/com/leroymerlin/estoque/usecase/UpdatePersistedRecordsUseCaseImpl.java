package br.com.leroymerlin.estoque.usecase;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.FileGateway;

@Component
class UpdatePersistedRecordsUseCaseImpl implements UpdatePersistedRecordsUseCase {

	private final FileGateway gateway;

	public UpdatePersistedRecordsUseCaseImpl(FileGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void update(Long fileId) {
		this.gateway.incrementPersistedRecordsFor(fileId);
	}

}

package com.easys.estoque.usecase;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.FileProblemGateway;

@Component
class SaveProcessingProblemUseCaseImpl implements SaveProcessingProblemUseCase {

	private final FileProblemGateway gateway;

	public SaveProcessingProblemUseCaseImpl(FileProblemGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void save(Long fileId, String payload, String error) {
		this.gateway.save(fileId, payload, error);
	}

}

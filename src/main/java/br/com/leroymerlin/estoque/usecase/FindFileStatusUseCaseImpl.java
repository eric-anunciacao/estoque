package br.com.leroymerlin.estoque.usecase;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.FileGateway;
import br.com.leroymerlin.estoque.infra.config.exception.ResourceNotFoundException;
import br.com.leroymerlin.estoque.usecase.response.FindFileStatusResponse;

@Component
class FindFileStatusUseCaseImpl implements FindFileStatusUseCase {

	private final FileGateway gateway;

	public FindFileStatusUseCaseImpl(FileGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public FindFileStatusResponse status(Long id) {
		var status = this.gateway.getFileStatus(id);

		if (StringUtils.isNotEmpty(status)) {
			return new FindFileStatusResponse(id, status);
		} else {
			throw new ResourceNotFoundException(String.format("File not found for this id :: %s", id));
		}
	}

}

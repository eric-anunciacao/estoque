package com.easys.estoque.usecase;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.FileGateway;
import com.easys.estoque.infra.config.exception.ResourceNotFoundException;
import com.easys.estoque.usecase.response.FindFileStatusResponse;

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

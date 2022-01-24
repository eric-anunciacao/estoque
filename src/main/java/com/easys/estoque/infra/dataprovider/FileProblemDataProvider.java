package com.easys.estoque.infra.dataprovider;

import org.springframework.stereotype.Component;

import com.easys.estoque.domain.gateway.FileProblemGateway;
import com.easys.estoque.infra.jpa.entity.FileProblemJpa;
import com.easys.estoque.infra.jpa.repository.FileProblemJpaRepository;

@Component
class FileProblemDataProvider implements FileProblemGateway {

	private final FileProblemJpaRepository repository;

	public FileProblemDataProvider(FileProblemJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Long fileId, String payload, String error) {
		var jpa = new FileProblemJpa();
		jpa.setFileId(fileId);
		jpa.setPayload(payload);
		jpa.setError(error);

		this.repository.save(jpa);
	}

}

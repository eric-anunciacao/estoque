package br.com.leroymerlin.estoque.infra.dataprovider;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.gateway.FileProblemGateway;
import br.com.leroymerlin.estoque.infra.jpa.entity.FileProblemJpa;
import br.com.leroymerlin.estoque.infra.jpa.repository.FileProblemJpaRepository;

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

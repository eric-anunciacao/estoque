package br.com.leroymerlin.estoque.infra.dataprovider;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.dto.SaveFileDto;
import br.com.leroymerlin.estoque.domain.gateway.FileGateway;
import br.com.leroymerlin.estoque.infra.jpa.entity.mapper.FileJpaMapper;
import br.com.leroymerlin.estoque.infra.jpa.repository.FileJpaRepository;

@Component
class FileDataProvider implements FileGateway {

	private final FileJpaRepository repository;

	public FileDataProvider(FileJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Long save(SaveFileDto dto) {
		return this.repository.save(FileJpaMapper.INSTANCE.toJpa(dto)).getId();
	}

	@Override
	@Transactional
	public void incrementPersistedRecordsFor(Long fileId) {
		this.repository.incrementPersistedRecordsFor(fileId);
	}

	@Override
	public String getFileStatus(Long id) {
		return this.repository.getStatus(id);
	}

}

package br.com.leroymerlin.estoque.domain.gateway;

import br.com.leroymerlin.estoque.domain.dto.SaveFileDto;

public interface FileGateway {

	Long save(SaveFileDto dto);

	void incrementPersistedRecordsFor(Long fileId);

}

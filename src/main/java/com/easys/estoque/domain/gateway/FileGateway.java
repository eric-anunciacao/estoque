package com.easys.estoque.domain.gateway;

import com.easys.estoque.domain.dto.SaveFileDto;

public interface FileGateway {

	Long save(SaveFileDto dto);

	void incrementPersistedRecordsFor(Long fileId);

	String getFileStatus(Long id);

}

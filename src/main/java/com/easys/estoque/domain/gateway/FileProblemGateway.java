package com.easys.estoque.domain.gateway;

public interface FileProblemGateway {

	void save(Long fileId, String payload, String error);

}

package com.easys.estoque.usecase;

public interface SaveProcessingProblemUseCase {

	void save(Long fileId, String payload, String error);

}

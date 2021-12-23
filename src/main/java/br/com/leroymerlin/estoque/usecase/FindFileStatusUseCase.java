package br.com.leroymerlin.estoque.usecase;

import br.com.leroymerlin.estoque.usecase.response.FindFileStatusResponse;

public interface FindFileStatusUseCase {

	FindFileStatusResponse status(Long id);

}

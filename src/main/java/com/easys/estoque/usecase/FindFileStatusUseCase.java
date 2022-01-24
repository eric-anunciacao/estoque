package com.easys.estoque.usecase;

import com.easys.estoque.usecase.response.FindFileStatusResponse;

public interface FindFileStatusUseCase {

	FindFileStatusResponse status(Long id);

}

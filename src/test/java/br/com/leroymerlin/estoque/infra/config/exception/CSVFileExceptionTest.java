package br.com.leroymerlin.estoque.infra.config.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CSVFileExceptionTest {

	@Test
	void shouldCreateException() {
		assertNotNull(new CSVFileException("Error", null));
	}

}

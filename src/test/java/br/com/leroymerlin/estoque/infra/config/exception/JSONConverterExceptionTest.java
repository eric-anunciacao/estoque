package br.com.leroymerlin.estoque.infra.config.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JSONConverterExceptionTest {

	@Test
	void shouldCreateObject() {
		assertNotNull(new JSONConverterException("Error", new IllegalArgumentException("")));
	}

}

package br.com.leroymerlin.estoque.cross;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import br.com.leroymerlin.estoque.infra.config.exception.CSVFileException;

class CSVUtilsTest {

	@Test
	void shouldThrowsException() throws IOException {
		var contentStream = this.getClass().getClassLoader().getResourceAsStream("products.csv");
		var file = new MockMultipartFile("file", "file", "text/pdf", contentStream);
		var exception = assertThrows(CSVFileException.class, () -> CSVUtils.convert(file, Object.class));

		assertNotNull(exception);
		assertEquals("This file isn't a CSV file", exception.getMessage());
	}

}

package br.com.leroymerlin.estoque.cross;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;

import br.com.leroymerlin.estoque.infra.config.exception.CSVFileException;

public class CSVUtils {

	private static final String CONTENT_TYPE = "text/csv";
	private static final char SEPARATOR = ';';

	private CSVUtils() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> convert(MultipartFile file, Class<T> clazz) {
		validate(file);

		try (var fileReader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			return new CsvToBeanBuilder(fileReader).withSeparator(SEPARATOR).withType(clazz).withSkipLines(0).build()
					.parse();
		} catch (IOException e) {
			throw new CSVFileException("Fail to parse CSV file", e);
		}
	}

	private static void validate(MultipartFile file) {
		if (!isCSVFormat(file)) {
			throw new CSVFileException("This file isn't a CSV file");
		}
	}

	private static boolean isCSVFormat(MultipartFile file) {
		return CONTENT_TYPE.equalsIgnoreCase(file.getContentType());
	}

}

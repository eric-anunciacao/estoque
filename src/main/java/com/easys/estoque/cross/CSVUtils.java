package com.easys.estoque.cross;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.easys.estoque.infra.config.exception.CSVFileException;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVUtils {

	private static final char SEPARATOR = ';';

	private CSVUtils() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> List<T> convert(InputStream is, Class<T> clazz) {
		try (var fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			return new CsvToBeanBuilder(fileReader).withSeparator(SEPARATOR).withType(clazz).withSkipLines(0).build()
					.parse();
		} catch (IOException e) {
			throw new CSVFileException("Fail to parse CSV file", e);
		}
	}

}

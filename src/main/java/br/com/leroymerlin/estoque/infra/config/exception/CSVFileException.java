package br.com.leroymerlin.estoque.infra.config.exception;

import java.io.IOException;

public class CSVFileException extends RuntimeException {

	private static final long serialVersionUID = -301041799501045952L;

	public CSVFileException(String message) {
		super(message);
	}

	public CSVFileException(String message, IOException e) {
		super(message, e);
	}

}

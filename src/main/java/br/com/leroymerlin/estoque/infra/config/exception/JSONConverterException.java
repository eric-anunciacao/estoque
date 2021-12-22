package br.com.leroymerlin.estoque.infra.config.exception;

public class JSONConverterException extends RuntimeException {

	private static final long serialVersionUID = -5458747530055984323L;

	public JSONConverterException(String message, Exception e) {
		super(message, e);
	}

}

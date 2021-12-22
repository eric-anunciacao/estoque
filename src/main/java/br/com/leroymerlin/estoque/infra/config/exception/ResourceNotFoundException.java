package br.com.leroymerlin.estoque.infra.config.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7546868289487932100L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}

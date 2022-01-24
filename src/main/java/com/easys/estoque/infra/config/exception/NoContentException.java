package com.easys.estoque.infra.config.exception;

public class NoContentException extends RuntimeException {

	private static final long serialVersionUID = 3374008349695185089L;

	public NoContentException(String message) {
		super(message);
	}

}

package com.easys.estoque.infra.config.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5634374592339904361L;

	public BusinessException(String message) {
		super(message);
	}

}

package br.com.leroymerlin.estoque.infra.config;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.leroymerlin.estoque.infra.config.exception.NoContentException;
import br.com.leroymerlin.estoque.infra.config.exception.ResourceNotFoundException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return handle(HttpStatus.NOT_FOUND, request, Arrays.asList(ex.getMessage()));
	}

	@ExceptionHandler(NoContentException.class)
	protected ResponseEntity<Object> handleNoContentException(NoContentException ex, WebRequest request) {
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(SQLException.class)
	protected ResponseEntity<Object> handleSQLException(SQLException ex, WebRequest request) {
		return handle(HttpStatus.INTERNAL_SERVER_ERROR, request, Arrays.asList(ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getField() + ": " + x.getDefaultMessage()).collect(Collectors.toList());

		return handle(status, request, errors);
	}

	private ResponseEntity<Object> handle(HttpStatus status, WebRequest request, List<String> errors) {
		var body = new LinkedHashMap<String, Object>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());
		body.put("errors", errors);

		if (request != null) {
			body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
		}

		return new ResponseEntity<>(body, status);
	}

}
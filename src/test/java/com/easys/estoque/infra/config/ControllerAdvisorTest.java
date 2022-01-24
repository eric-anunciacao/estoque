package com.easys.estoque.infra.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.easys.estoque.infra.config.exception.NoContentException;

@ExtendWith(MockitoExtension.class)
class ControllerAdvisorTest {

	@Test
	void deveTratarNoContentException() {
		var advisor = new ControllerAdvisor();
		var exception = new NoContentException("");

		var response = advisor.handleNoContentException(exception, null);

		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void deveTratarSQLException() {
		var advisor = new ControllerAdvisor();
		var exception = new SQLException();

		var response = advisor.handleSQLException(exception, null);

		assertNotNull(response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	void deveTratarMethodArgumentNotValidException() {
		var advisor = new ControllerAdvisor();
		var status = HttpStatus.BAD_GATEWAY;

		MethodParameter parameter = mock(MethodParameter.class);
		BindingResult bindingResult = mock(BindingResult.class);
		var exception = new MethodArgumentNotValidException(parameter, bindingResult);

		var response = advisor.handleMethodArgumentNotValid(exception, null, status, null);

		assertNotNull(response);
		assertEquals(status, response.getStatusCode());
	}

}
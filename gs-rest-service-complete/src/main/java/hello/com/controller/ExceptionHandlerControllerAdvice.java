package hello.com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import hello.com.exception.ExceptionResponse;
import hello.com.exception.GreetingException;
import hello.com.exception.ValidationException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(GreetingException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleGreetingException(final Exception exception, final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());

		return error;
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
	public @ResponseBody ExceptionResponse handleValidationException(final Exception exception, final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());

		return error;
	}
}

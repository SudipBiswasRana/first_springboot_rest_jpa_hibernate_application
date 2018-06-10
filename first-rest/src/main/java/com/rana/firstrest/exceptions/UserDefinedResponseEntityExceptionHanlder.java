package com.rana.firstrest.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rana.firstrest.model.ExceptionBody;

@ControllerAdvice
@RestController
public class UserDefinedResponseEntityExceptionHanlder extends ResponseEntityExceptionHandler {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(Exception.class)
	public final ResponseEntity handleAllException(Exception e, WebRequest request) {
		ExceptionBody exceptionInUse = new ExceptionBody(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionInUse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ReaderNotFoundException.class)
	public final ResponseEntity handleReaderFotFoundException(Exception e, WebRequest request) {
		ExceptionBody exceptionInUse = new ExceptionBody(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionInUse, HttpStatus.NOT_FOUND);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionBody exceptionInUse = new ExceptionBody(new Date(), "Validation Faild",
				e.getBindingResult().toString());
		return new ResponseEntity(exceptionInUse, HttpStatus.BAD_REQUEST);

	}
}

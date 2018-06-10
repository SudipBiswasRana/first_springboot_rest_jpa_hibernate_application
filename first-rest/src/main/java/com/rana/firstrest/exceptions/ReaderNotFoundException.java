package com.rana.firstrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReaderNotFoundException extends RuntimeException {

	public ReaderNotFoundException(String message) {
		super(message);

	}

}

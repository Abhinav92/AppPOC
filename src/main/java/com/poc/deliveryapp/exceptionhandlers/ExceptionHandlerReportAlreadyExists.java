package com.poc.deliveryapp.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExceptionHandlerReportAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = -7277038601802015695L;

	public ExceptionHandlerReportAlreadyExists() {
	}

}

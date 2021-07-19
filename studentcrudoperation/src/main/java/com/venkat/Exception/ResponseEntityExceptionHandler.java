package com.venkat.Exception;

public class ResponseEntityExceptionHandler extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public ResponseEntityExceptionHandler(String message) {

		super(message);

	}

	public ResponseEntityExceptionHandler(String message , Throwable throwable) {

		super(message , throwable);

	}




}

package com.venkat.Exception;

public class StudentResourceNotFoundException extends RuntimeException{
	 
	private static final long serialVersionUID = 1L;

	public StudentResourceNotFoundException(String message)
	{
		super(message);
	}
	
	public StudentResourceNotFoundException(String message , Throwable throwable)
	{
		super(message , throwable);
	}

}

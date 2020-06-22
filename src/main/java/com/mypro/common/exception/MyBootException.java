package com.mypro.common.exception;

public class MyBootException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MyBootException(String message){
		super(message);
	}
	
	public MyBootException(Throwable cause)
	{
		super(cause);
	}
	
	public MyBootException(String message, Throwable cause)
	{
		super(message,cause);
	}
}

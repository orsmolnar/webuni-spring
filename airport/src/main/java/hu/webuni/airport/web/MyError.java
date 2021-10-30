package hu.webuni.airport.web;

import java.util.List;

import org.springframework.validation.FieldError;

public class MyError {

	private String message;
	private int errorCode;
	List<FieldError> fielsErrors;
	
	public MyError(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public List<FieldError> getFielsErrors() {
		return fielsErrors;
	}
	public void setFielsErrors(List<FieldError> fielsErrors) {
		this.fielsErrors = fielsErrors;
	}
	
}

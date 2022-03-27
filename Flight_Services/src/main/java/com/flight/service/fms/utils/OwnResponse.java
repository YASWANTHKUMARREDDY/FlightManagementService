package com.flight.service.fms.utils;

import org.springframework.http.HttpStatus;

public class OwnResponse<T> {

	private String message;

	private T result;

	private Boolean status;

	private HttpStatus httpStatus;

	public OwnResponse() {
		super();
	}

	public OwnResponse(String message, Boolean status, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.status = status;
		this.httpStatus = httpStatus;
	}

	public OwnResponse(String message, T result, Boolean status, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.result = result;
		this.status = status;
		this.httpStatus = httpStatus;
	}

	public OwnResponse(T result, Boolean status, HttpStatus httpStatus) {
		super();
		this.result = result;
		this.status = status;
		this.httpStatus = httpStatus;
	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}

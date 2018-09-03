package com.referminds.userpostservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JobPostNotFoundException extends RuntimeException {
	public JobPostNotFoundException(String message) {
		super(message);
	}
}


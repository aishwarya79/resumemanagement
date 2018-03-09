package com.grab.resume.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grab.resume.dto.response.ResumeMgmtResponse;
import com.grab.resume.exception.DataNotFoundException;
import com.grab.resume.exception.InvalidInputException;

@RestControllerAdvice
public class ResumeMgmtControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ResumeMgmtControllerAdvice.class);

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ResumeMgmtResponse> handleInvalidInputException(InvalidInputException ex) {
		logger.error("InvalidInputException: ", ex);
		ResumeMgmtResponse errorResponse = new ResumeMgmtResponse(false);
		errorResponse.setErrorMsg(ex.getMessage());
		return new ResponseEntity<ResumeMgmtResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ResumeMgmtResponse> handleDataNotFoundException(DataNotFoundException ex) {
		logger.error("DataNotFoundException: ", ex);
		ResumeMgmtResponse errorResponse = new ResumeMgmtResponse(false);
		errorResponse.setErrorMsg(ex.getMessage());
		return new ResponseEntity<ResumeMgmtResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResumeMgmtResponse> handleGenericInput(Exception ex) {
		logger.error("General Exception: ", ex);
		ResumeMgmtResponse errorResponse = new ResumeMgmtResponse(false);
		errorResponse.setErrorMsg(ex.getMessage());
		return new ResponseEntity<ResumeMgmtResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

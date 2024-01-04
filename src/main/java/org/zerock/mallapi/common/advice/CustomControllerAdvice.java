package org.zerock.mallapi.common.advice;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice @Log4j2
public class CustomControllerAdvice {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElement(NoSuchElementException e) {
		if (log.isDebugEnabled()) {log.debug("Start CustomControllerAdvice.handleNoSuchElement");}
		String msg = e.getMessage();
		if (log.isDebugEnabled()) {log.debug("msg ::: " + msg);}
		if (log.isDebugEnabled()) {log.debug("End CustomControllerAdvice.handleNoSuchElement");}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg", msg));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		if (log.isDebugEnabled()) {log.debug("Start CustomControllerAdvice.handleMethodArgumentNotValid");}
		String msg = e.getMessage();
		if (log.isDebugEnabled()) {log.debug("msg ::: " + msg);}
		if (log.isDebugEnabled()) {log.debug("End CustomControllerAdvice.handleMethodArgumentNotValid");}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg", msg));
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
		if (log.isDebugEnabled()) {log.debug("Start CustomControllerAdvice.handleMethodArgumentTypeMismatch");}
		String msg = e.getMessage();
		if (log.isDebugEnabled()) {log.debug("msg ::: " + msg);}
		if (log.isDebugEnabled()) {log.debug("End CustomControllerAdvice.handleMethodArgumentTypeMismatch");}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg", msg));
	}
}

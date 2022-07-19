package ru.svyat.pdapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleRuntime(RuntimeException ex){
		log.warn(ex.getLocalizedMessage(), ex);
		return ResponseEntity.status(500).body(ex.getLocalizedMessage());
	}
}

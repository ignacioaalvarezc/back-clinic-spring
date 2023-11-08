package com.voll.api.infrastructure.config.exception;

// IMPORTS.
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

/**
 * THIS EXCEPTION HANDLER CLASS HANDLE COMMON ERRORS IN THE APPLICATION.
 * This class contains exception handlers for different types of errors and returns appropriate responses
 * based on the type of exception thrown during execution.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@RestControllerAdvice
public class ErrorHandler {

	/**
	 * EXCEPTION HANDLER FOR ENTITY NOT FOUND EXCEPTION.
	 * Returns a "Not Found" (HTTP 404) response when the requested entity is not found.
	 *
	 * @return ResponseEntity with a "Not Found" response.
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity handleError404() {
		return ResponseEntity.notFound().build();
	}

	/**
	 * EXCEPTION HANDLER FOR METHOD ARGUMENT NOT VALID EXCEPTION.
	 * Returns a "Bad Request" (HTTP 400) response with details about data validation errors.
	 *
	 * @param e MethodArgumentNotValidException containing information about validation errors.
	 * @return ResponseEntity with a "Bad Request" response and details about validation errors.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleError400(MethodArgumentNotValidException e) {
		var errors = e.getFieldErrors().stream().map(DataValidationError::new).toList();
		return ResponseEntity.badRequest().body(errors);
	}

	/**
	 * EXCEPTION HANDLER FOR INTEGRITY VALIDATION.
	 * Returns a "Bad Request" (HTTP 400) response with the provided error message.
	 *
	 * @param e IntegrityValidation exception containing the error message.
	 * @return ResponseEntity with a "Bad Request" response and the error message.
	 */
	@ExceptionHandler(IntegrityValidation.class)
	public ResponseEntity errorHandlerIntegrityValidations(Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * EXCEPTION HANDLER FOR VALIDATION EXCEPTION.
	 * Returns a "Bad Request" (HTTP 400) response with the provided error message.
	 *
	 * @param e ValidationException containing the error message.
	 * @return ResponseEntity with a "Bad Request" response and the error message.
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity errorHandlerBusinessValidations(Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * INNER CLASS TO REPRESENT DATA VALIDATION ERRORS.
	 * This inner class is used to structure validation errors before returning them as a response.
	 *
	 * @param field Name of the field that validation failed
	 * @param error Error message associated with the specific field
	 */
	private record DataValidationError(String field, String error) {
		public DataValidationError(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}

}

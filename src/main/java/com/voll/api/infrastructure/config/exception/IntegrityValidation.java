package com.voll.api.infrastructure.config.exception;

/**
 * THIS CUSTOM CLASS REPRESENTING INTEGRITY VALIDATION FAILURES.
 * This exception is thrown when there are issues related to data integrity and validation in the application.
 * It extends the RuntimeException class for unchecked exceptions.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public class IntegrityValidation extends RuntimeException {
    /**
     * CONSTRUCTS A NEW INTEGRITY VALIDATION EXCEPTION WITH THE SPECIFIED ERROR MESSAGE.
     *
     * @param message The error message describing the integrity validation failure.
     */
    public IntegrityValidation(String message) { super(message); }
}

package com.HumanResourceManagement.shared.exception;

/**
 * Thrown when an operation would violate a uniqueness rule (duplicate email,
 * duplicate attendance record for the same day, overlapping leave request,
 * etc.). Handled globally by {@link GlobalExceptionHandler}, which translates
 * it into a 409 CONFLICT response.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}

package com.HumanResourceManagement.shared.exception;

/**
 * Thrown whenever a requested entity (Employee, Department, Leave request,
 * etc.) cannot be found by its identifier. Handled globally by
 * {@link GlobalExceptionHandler}, which translates it into a 404 NOT FOUND
 * response.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Convenience factory: ResourceNotFoundException.of("Employee", id)
     * -> "Employee not found with id: 42"
     */
    public static ResourceNotFoundException of(String resourceName, Object id) {
        return new ResourceNotFoundException(resourceName + " not found with id: " + id);
    }
}

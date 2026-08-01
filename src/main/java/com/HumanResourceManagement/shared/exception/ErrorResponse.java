package com.HumanResourceManagement.shared.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

/**
 * Uniform JSON body returned for every error handled by
 * {@link GlobalExceptionHandler}, e.g.:
 *
 * <pre>
 * {
 *   "timestamp": "2026-07-31T10:15:30",
 *   "status": 404,
 *   "error": "Not Found",
 *   "message": "Employee not found with id: 42",
 *   "path": "/api/employees/42",
 *   "fieldErrors": null
 * }
 * </pre>
 */
@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    /** Populated only for bean-validation failures: fieldName -> error message. */
    private Map<String, String> fieldErrors;
}

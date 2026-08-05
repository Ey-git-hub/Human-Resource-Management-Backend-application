package com.HumanResourceManagement.Employee.DTO;

import com.HumanResourceManagement.Employee.Model.EmployeeDocument;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

@NoArgsConstructor
public class EmployeeDocumentResponse {
    private Long id;
    private Long employeeId;
    private EmployeeDocument.DocumentType documentType;
    private String fileName;
    private String fileUrl;
    private LocalDateTime uploadedAt;
    private LocalDate expiryDate;
    private boolean verified;
}

package com.HumanResourceManagement.Employee.DTO;

import com.HumanResourceManagement.Employee.Model.EmployeeDocument;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDocumentRequest {
    @NotNull
    private Long employeeId;

    @NotNull
    private EmployeeDocument.DocumentType documentType;

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileUrl;

    private LocalDate expiryDate;

    private boolean verified;
}

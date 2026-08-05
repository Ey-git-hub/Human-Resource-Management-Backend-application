package com.HumanResourceManagement.Employee.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentRequest;
import com.HumanResourceManagement.Employee.DTO.EmployeeDocumentResponse;
import com.HumanResourceManagement.Employee.Model.EmployeeDocument;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class EmployeeDocumentMapper {

    public EmployeeDocumentResponse toResponse(EmployeeDocument doc) {
        EmployeeDocumentResponse resp = MapperUtils.map(doc, EmployeeDocumentResponse.class);
        if (doc.getEmployee() != null) {
            resp.setEmployeeId(doc.getEmployee().getId());
        }
        return resp;
    }

    public EmployeeDocument toEntity(EmployeeDocumentRequest req, Employee employee) {
        EmployeeDocument doc = MapperUtils.map(req, EmployeeDocument.class);
        doc.setEmployee(employee);
        return doc;
    }

    public void updateEntity(EmployeeDocument existing, EmployeeDocumentRequest req, Employee employee) {
        MapperUtils.copy(req, existing);
        if (employee != null) existing.setEmployee(employee);
    }
}

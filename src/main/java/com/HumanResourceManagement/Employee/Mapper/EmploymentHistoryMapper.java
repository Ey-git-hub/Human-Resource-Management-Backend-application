package com.HumanResourceManagement.Employee.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentHistoryResponse;
import com.HumanResourceManagement.Employee.Model.EmploymentHistory;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Organization.Model.Position;
import com.HumanResourceManagement.Organization.Model.Department;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class EmploymentHistoryMapper {

    public EmploymentHistoryResponse toResponse(EmploymentHistory h) {
        EmploymentHistoryResponse resp = MapperUtils.map(h, EmploymentHistoryResponse.class);
        if (h.getEmployee() != null) resp.setEmployeeId(h.getEmployee().getId());
        if (h.getPreviousPosition() != null) resp.setPreviousPositionId(h.getPreviousPosition().getId());
        if (h.getNewPosition() != null) resp.setNewPositionId(h.getNewPosition().getId());
        if (h.getPreviousDepartment() != null) resp.setPreviousDepartmentId(h.getPreviousDepartment().getId());
        if (h.getNewDepartment() != null) resp.setNewDepartmentId(h.getNewDepartment().getId());
        if (h.getApprovedBy() != null) resp.setApprovedById(h.getApprovedBy().getId());
        return resp;
    }

    public EmploymentHistory toEntity(EmploymentHistoryRequest req, Employee employee,
                                       Position prevPos, Position newPos,
                                       Department prevDept, Department newDept,
                                       Employee approvedBy) {
        EmploymentHistory h = MapperUtils.map(req, EmploymentHistory.class);
        h.setEmployee(employee);
        h.setPreviousPosition(prevPos);
        h.setNewPosition(newPos);
        h.setPreviousDepartment(prevDept);
        h.setNewDepartment(newDept);
        h.setApprovedBy(approvedBy);
        return h;
    }

    public void updateEntity(EmploymentHistory existing, EmploymentHistoryRequest req,
                             Position prevPos, Position newPos,
                             Department prevDept, Department newDept,
                             Employee approvedBy) {
        MapperUtils.copy(req, existing);
        existing.setPreviousPosition(prevPos);
        existing.setNewPosition(newPos);
        existing.setPreviousDepartment(prevDept);
        existing.setNewDepartment(newDept);
        existing.setApprovedBy(approvedBy);
    }
}

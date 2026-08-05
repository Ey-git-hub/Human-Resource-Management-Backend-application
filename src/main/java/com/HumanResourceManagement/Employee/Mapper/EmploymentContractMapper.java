package com.HumanResourceManagement.Employee.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.DTO.EmploymentContractRequest;
import com.HumanResourceManagement.Employee.DTO.EmploymentContractResponse;
import com.HumanResourceManagement.Employee.Model.EmploymentContract;
import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class EmploymentContractMapper {

    public EmploymentContractResponse toResponse(EmploymentContract c) {
        EmploymentContractResponse resp = MapperUtils.map(c, EmploymentContractResponse.class);
        if (c.getEmployee() != null) resp.setEmployeeId(c.getEmployee().getId());
        return resp;
    }

    public EmploymentContract toEntity(EmploymentContractRequest req, Employee employee) {
        EmploymentContract c = MapperUtils.map(req, EmploymentContract.class);
        c.setEmployee(employee);
        return c;
    }

    public void updateEntity(EmploymentContract existing, EmploymentContractRequest req, Employee employee) {
        MapperUtils.copy(req, existing);
        if (employee != null) existing.setEmployee(employee);
    }
}

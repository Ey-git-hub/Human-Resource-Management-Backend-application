package com.HumanResourceManagement.application.dto;

import com.HumanResourceManagement.application.model.Department;
import lombok.Data;
//import com.HumanResourceManagement.application.model.Employee;

import java.time.LocalDateTime;
@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private Long managerId;
    private String managerName;
    private String Description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public static DepartmentResponse fromEntity(Department department){
        DepartmentResponse  response=new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());
        response.setDescription(department.getDescription());
        response.setCreatedAt(department.getCreatedAt());
        response.setUpdatedAt(department.getUpdatedAt());
        if(department.getManager()!=null){
            response.setManagerId(department.getManager().getId());
            response.setManagerName(department.getManager().getFirstName()+" "+department.getManager().getLastName());
        }

        return response;

    }
}

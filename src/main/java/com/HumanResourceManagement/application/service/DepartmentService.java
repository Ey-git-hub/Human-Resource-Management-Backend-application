package com.HumanResourceManagement.application.service;

import com.HumanResourceManagement.application.dto.DepartmentRequest;
import com.HumanResourceManagement.application.dto.DepartmentResponse;
import com.HumanResourceManagement.application.model.Department;
import com.HumanResourceManagement.application.model.Employee;
import com.HumanResourceManagement.application.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponse> fetchAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentResponse::fromEntity)
                .collect(Collectors.toList());
    }


    public Optional<DepartmentResponse> getDepartment(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentResponse::fromEntity);

    }


    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if(departmentRepository.existsByName(request.getName())){
            throw new IllegalArgumentException("department already exists with name : "+request.getName());
        }
        Department department=new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        //i will continue this
//        if(request.getManagerName()!=null){
//            Employee manager=
//        }
       return DepartmentResponse.fromEntity(departmentRepository.save(department));

    }
}

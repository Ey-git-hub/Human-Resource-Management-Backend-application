package com.HumanResourceManagement.Organization.DTO;

import com.HumanResourceManagement.Organization.Model.Branch.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class BranchResponse {

    private Long id;
    private Long organizationId;
    private String organizationName;
    private String name;
    private String code;
    private String address;
    private String city;
    private String region;
    private String country;
    private String phone;
    private String email;
    private boolean isHeadquarters;
    private Status status;
}
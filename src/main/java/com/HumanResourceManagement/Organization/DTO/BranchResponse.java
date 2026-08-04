package com.HumanResourceManagement.Organization.DTO;

import com.HumanResourceManagement.Organization.Model.Branch.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
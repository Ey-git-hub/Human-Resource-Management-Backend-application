package com.HumanResourceManagement.Organization.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponse {

    private Long id;

    private Long organizationId;
    private String name;
    private String code;
    private String address;
    private String city;
    private String region;
    private String country;
    private String phone;
    private String email;
    private Boolean headquarters;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

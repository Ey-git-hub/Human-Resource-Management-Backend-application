package com.HumanResourceManagement.Organization.Model;

// import com.HumanResourceManagement.shared.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "organizations")

public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false)
    // private String name;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "tax_id")
    private String taxId;

    private String industry;
    private String address;
    private String phone;
    private String email;
    private String website;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "founded_date")
    private LocalDate foundedDate;
}
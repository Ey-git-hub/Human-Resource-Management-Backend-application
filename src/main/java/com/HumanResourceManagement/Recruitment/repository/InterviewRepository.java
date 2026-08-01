package com.HumanResourceManagement.Recruitment.repository;

import com.HumanResourceManagement.Recruitment.Model.Interview;
import com.HumanResourceManagement.Recruitment.Model.Interview.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    // Find all interviews for a given Job Application
    List<Interview> findByJobApplicationId(Long jobApplicationId);
    Page<Interview> findByJobApplicationId(Long jobApplicationId, Pageable pageable);

    // Find all interviews assigned to a specific Employee
    List<Interview> findByInterviewerId(Long interviewerId);
    Page<Interview> findByInterviewerId(Long interviewerId, Pageable pageable);

    // Find interviews filtered by status (SCHEDULED, COMPLETED, CANCELLED)
    List<Interview> findByStatus(Status status);
    Page<Interview> findByStatus(Status status, Pageable pageable);
}
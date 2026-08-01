package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Recruitment.Model.Interview;
import com.HumanResourceManagement.Recruitment.Model.JobApplication;
import com.HumanResourceManagement.Recruitment.dto.InterviewRequest;
import com.HumanResourceManagement.Recruitment.dto.InterviewResponse;

@Component
public class InterviewMapper {

    public InterviewResponse toResponse(Interview interview) {
        return InterviewResponse.fromEntity(interview);
    }

    public Interview toEntity(InterviewRequest request, JobApplication jobApplication, Employee interviewer) {
        return request.toEntity(jobApplication, interviewer);
    }

    public void updateEntity(Interview existing, InterviewRequest request, JobApplication jobApplication, Employee interviewer) {
        existing.setJobApplication(jobApplication);
        existing.setInterviewer(interviewer);
        existing.setScheduledDate(request.getScheduledDate());
        existing.setMode(request.getMode());
        existing.setLocation(request.getLocation());
        existing.setStatus(request.getStatus());
        existing.setFeedback(request.getFeedback());
        existing.setRating(request.getRating());
    }
}

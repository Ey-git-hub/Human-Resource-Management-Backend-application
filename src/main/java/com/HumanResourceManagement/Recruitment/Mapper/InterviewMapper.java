package com.HumanResourceManagement.Recruitment.Mapper;

import org.springframework.stereotype.Component;

import com.HumanResourceManagement.Employee.Model.Employee;
import com.HumanResourceManagement.Recruitment.Model.Interview;
import com.HumanResourceManagement.Recruitment.Model.JobApplication;
import com.HumanResourceManagement.Recruitment.dto.InterviewRequest;
import com.HumanResourceManagement.Recruitment.dto.InterviewResponse;
import com.HumanResourceManagement.shared.util.MapperUtils;

@Component
public class InterviewMapper {

    public InterviewResponse toResponse(Interview interview) {
        InterviewResponse response = MapperUtils.map(interview, InterviewResponse.class);
        if (interview.getJobApplication() != null) {
            response.setJobApplicationId(interview.getJobApplication().getId());
        }
        if (interview.getInterviewer() != null) {
            response.setInterviewerId(interview.getInterviewer().getId());
            response.setInterviewerName(interview.getInterviewer().getFirstName() + " " + interview.getInterviewer().getLastName());
        }
        return response;
    }

    public Interview toEntity(InterviewRequest request, JobApplication jobApplication, Employee interviewer) {
        Interview interview = MapperUtils.map(request, Interview.class);
        interview.setJobApplication(jobApplication);
        interview.setInterviewer(interviewer);
        return interview;
    }

    public void updateEntity(Interview existing, InterviewRequest request, JobApplication jobApplication, Employee interviewer) {
        MapperUtils.copy(request, existing);
        existing.setJobApplication(jobApplication);
        existing.setInterviewer(interviewer);
    }
}

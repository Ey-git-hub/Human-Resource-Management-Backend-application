package com.HumanResourceManagement.Recruitment.service;

import java.util.List;

import com.HumanResourceManagement.Recruitment.dto.InterviewRequest;
import com.HumanResourceManagement.Recruitment.dto.InterviewResponse;

public interface InterviewServiceInterface {
    InterviewResponse createInterview(InterviewRequest requestDto);

    InterviewResponse getInterviewById(Long id);

    List<InterviewResponse> getAllInterviews();

    List<InterviewResponse> getInterviewsByJobApplication(Long jobApplicationId);

    List<InterviewResponse> getInterviewsByInterviewer(Long interviewerId);

    InterviewResponse updateInterview(Long id, InterviewRequest requestDto);

    void deleteInterview(Long id);
}

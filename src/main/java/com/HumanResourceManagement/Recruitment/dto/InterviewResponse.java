package com.HumanResourceManagement.Recruitment.dto;

import com.HumanResourceManagement.Recruitment.Model.Interview.Mode;
import com.HumanResourceManagement.Recruitment.Model.Interview.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InterviewResponse {

    private Long id;
    private Long jobApplicationId;
    private Long interviewerId;
    private String interviewerName;
    private LocalDateTime scheduledDate;
    private Mode mode;
    private String location;
    private Status status;
    private String feedback;
    private int rating;
}
package com.HumanResourceManagement.Recruitment.dto;

import com.HumanResourceManagement.Recruitment.Model.Interview.Mode;
import com.HumanResourceManagement.Recruitment.Model.Interview.Status;
// import com.HumanResourceManagement.Recruitment.Model.JobApplication;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewRequest {

    @NotNull(message = "Job Application ID is required")
    private Long jobApplicationId;

    @NotNull(message = "Interviewer ID is required")
    private Long interviewerId;

    @NotNull(message = "Scheduled date is required")
    private LocalDateTime scheduledDate;

    @NotNull(message = "Interview mode is required")
    private Mode mode;

    private String location;

    @NotNull(message = "Interview status is required")
    private Status status = Status.SCHEDULED;

    private String feedback;

    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    private int rating;
}
package com.Taskzy.Task.SubmissionService.service;

import com.Taskzy.Task.SubmissionService.model.Submission;

import java.util.List;

public interface SubmissionService
{
    Submission submitTask(Long taskId,String githubLink,Long userId,String jwt) throws Exception;

    Submission getTaskSubmissionById(Long submissionId)throws Exception;

    List<Submission> getAllTaskSubmissions() throws Exception;

    List<Submission> getTaskSubmissionByTaskId(Long taskId) throws  Exception;

    Submission acceptDeclineSubmission(Long id, String Status) throws Exception;

}

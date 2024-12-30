package com.Taskzy.Task.SubmissionService.service.impl;

import com.Taskzy.Task.SubmissionService.model.Submission;
import com.Taskzy.Task.SubmissionService.model.TaskDto;
import com.Taskzy.Task.SubmissionService.repository.SubmissionServiceRepo;
import com.Taskzy.Task.SubmissionService.service.SubmissionService;
import com.Taskzy.Task.SubmissionService.service.TaskService;
import com.Taskzy.Task.SubmissionService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionServiceRepo submissionServiceRepo;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;


    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userId,String jwt) throws Exception {
        TaskDto task= taskService.getTaskById(taskId,jwt);
        if(task!=null){
            Submission submission=new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionServiceRepo.save(submission);
        }
        throw new Exception("task not found with this id "+taskId);
    }



    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return submissionServiceRepo.findById(submissionId).orElseThrow(()->new Exception("task submission failed with the id"+submissionId));
    }

    @Override
    public List<Submission> getAllTaskSubmissions() throws Exception {

        return submissionServiceRepo.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionByTaskId(Long taskId) throws Exception {
         return   submissionServiceRepo.findByTaskId(taskId);


    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String Status) throws Exception {
        Submission submission=getTaskSubmissionById(id);
        submission.setStatus(Status);
        if(Status.equals("ACCEPT")){

        taskService.completeTask(submission.getTaskId());
        }
         return submissionServiceRepo.save(submission);

    }
}

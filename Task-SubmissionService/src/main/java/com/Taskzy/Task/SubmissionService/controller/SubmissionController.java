package com.Taskzy.Task.SubmissionService.controller;

import com.Taskzy.Task.SubmissionService.model.Submission;
import com.Taskzy.Task.SubmissionService.model.UserDto;
import com.Taskzy.Task.SubmissionService.service.SubmissionService;
import com.Taskzy.Task.SubmissionService.service.TaskService;
import com.Taskzy.Task.SubmissionService.service.UserService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;


    @PostMapping()
    public ResponseEntity<Submission> submitTask(@RequestParam Long taskId,
                                                 @RequestParam String githubLink,
                                                 @RequestHeader ("Authorization") String jwt) throws  Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        Submission submission =submissionService.submitTask(taskId,githubLink, userDto.getId(), jwt);
        return new ResponseEntity<>(submission, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id,@RequestHeader ("Authorization") String jwt)throws  Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        Submission submission=submissionService.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions(@RequestHeader ("Authorization") String jwt)throws  Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        List<Submission> submission=submissionService.getAllTaskSubmissions();
            return new ResponseEntity<>(submission,HttpStatus.OK);

    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllTaskSubmissions(@PathVariable Long taskId,@RequestHeader ("Authorization") String jwt)throws  Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        List<Submission> submission=submissionService.getTaskSubmissionByTaskId(taskId);
        return new ResponseEntity<>(submission,HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> accepOrDeclineSubmission(@PathVariable Long id,
                                                                     @RequestParam("status") String status,
                                                                     @RequestHeader ("Authorization") String jwt)throws  Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        Submission submission=submissionService.acceptDeclineSubmission(id,status);
        return new ResponseEntity<>(submission,HttpStatus.OK);

    }



}

package com.Taskzy.Task.SubmissionService.service;


import com.Taskzy.Task.SubmissionService.model.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE",url = "http://localhost:5002/")
public interface TaskService {
    @GetMapping("/api/tasks/{id}")
        public TaskDto getTaskById(@PathVariable Long id,
                                                   @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/tasks/{taskId}/complete")
    public TaskDto completeTask(@PathVariable Long taskId) throws Exception;

}

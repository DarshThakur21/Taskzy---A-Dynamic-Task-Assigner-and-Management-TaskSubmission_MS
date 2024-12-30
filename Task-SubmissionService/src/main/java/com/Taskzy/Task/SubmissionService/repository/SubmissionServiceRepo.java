package com.Taskzy.Task.SubmissionService.repository;

import com.Taskzy.Task.SubmissionService.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionServiceRepo extends JpaRepository<Submission,Long> {

    List<Submission> findByTaskId(Long TaskId);

}

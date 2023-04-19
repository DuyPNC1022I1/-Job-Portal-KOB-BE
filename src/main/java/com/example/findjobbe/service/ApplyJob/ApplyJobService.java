package com.example.findjobbe.service.ApplyJob;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.model.User;
import com.example.findjobbe.repository.ApplyJobRepository;
import com.example.findjobbe.repository.JobRepository;
import com.example.findjobbe.repository.UserRepository;
import com.example.findjobbe.service.UserService;
import com.example.findjobbe.service.jobs.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyJobService {
    @Autowired
    ApplyJobRepository applyJobRepository;
    @Autowired
    UserService userService;
    @Autowired
    JobService jobService;

    public boolean applyJob(Long userId,Long jobId){
        User user = userService.findOne(userId);
        Job job = jobService.findOne(jobId);
        if (user!=null && job!=null){


        }

    }
}

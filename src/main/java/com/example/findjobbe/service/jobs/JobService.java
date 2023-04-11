package com.example.findjobbe.service.jobs;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService extends ICoreServiceJob{
    @Autowired
    private JobRepository jobRepository;
    public List<Job> findAllByCompany(Long companyId) {
        return jobRepository.findAllByCompany_Id(companyId);
    }
}

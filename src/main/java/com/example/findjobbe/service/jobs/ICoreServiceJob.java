package com.example.findjobbe.service.jobs;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.repository.JobRepository;
import com.example.findjobbe.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ICoreServiceJob implements ICoreService<Job> {
    @Autowired
    private JobRepository jobRepository;
    @Override
    public Page<Job> findAll(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    @Override
    public Job findOne(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void delete(Long id) {
        jobRepository.deleteById(id);
    }

}

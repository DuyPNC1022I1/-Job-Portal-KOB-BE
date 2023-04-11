package com.example.findjobbe.controller;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.service.jobs.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<Page<Job>> findAll(@PageableDefault(page = 5) Pageable pageable) {
        return new ResponseEntity<> (jobService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(jobService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Job job) {
        jobService.save(job);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findByCompany/{id}")
    public ResponseEntity<List<Job>> findAllByCompany(Long id) {
        return new ResponseEntity<>(jobService.findAllByCompany(id), HttpStatus.OK);
    }

}

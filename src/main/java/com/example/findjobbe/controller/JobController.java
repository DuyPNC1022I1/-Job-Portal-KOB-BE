package com.example.findjobbe.controller;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.model.SearchAll;
import com.example.findjobbe.model.SearchKey;
import com.example.findjobbe.service.ApplyJob.ApplyJobService;
import com.example.findjobbe.service.jobs.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private ApplyJobService applyJobService;

    //    @GetMapping
//    public ResponseEntity<Page<Job>> findAll(@PageableDefault(page = 5) Pageable pageable) {
//        return new ResponseEntity<> (jobService.findAll(pageable), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<Page<Job>> findAllTest(@PageableDefault(size = 5) Pageable pageable) {
        Page<Job> jobs = jobService.findAllTest(pageable);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findOne(@PathVariable Long id) {
        if (jobService.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(jobService.findOne(id), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Job job) {
        job.setStartDate(java.time.LocalDate.now());
        try {
            jobService.save(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Get data listJob by Company
    @GetMapping("/findByCompany/{id}")
    public ResponseEntity<Page<Job>> findAllByCompany(@PathVariable Long id,@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(jobService.findAllByCompanyPage(id,pageable), HttpStatus.OK);
    }

    //Block Job
    @GetMapping("/blockOrUnlockJob/{id}")
    public ResponseEntity<Page<Job>> blockOrUnlockJob(@PathVariable Long id,@PageableDefault Pageable pageable) {
       if (applyJobService.lockOrUnlockJob(id)){
           return new ResponseEntity<>(jobService.findAllByCompanyPage(jobService.findOne(id).getCompany().getId(),pageable), HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Unlock job
    @GetMapping("/unlockJob/{id}")
    public ResponseEntity<Page<Job>> unlockJob(@PathVariable Long id,@PageableDefault Pageable pageable) {
        Job jobUnlock = jobService.findOne(id);
        jobUnlock.setStatus(true);
        jobService.save(jobUnlock);
        return new ResponseEntity<>(jobService.findAllTest(pageable), HttpStatus.OK);
    }

//    @GetMapping("/find-by-name")
//    public ResponseEntity<List<Job>> findAllByJobName(@RequestParam("name") String name) {
//        if (!name.equals("")) {
//            return new ResponseEntity<>(jobService.findAllByJobName(name), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(jobService.findALl(), HttpStatus.OK);
//    }

    @GetMapping("/find-by-address")
    public ResponseEntity<Page<Job>> findAllByAddress(@RequestParam("address") String address, @PageableDefault Pageable pageable) {
        if (!address.equals("")) {
            return new ResponseEntity<>(jobService.findALlByCompanyAddress(address, pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(jobService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/find-by-career/{id}")
    public ResponseEntity<Page<Job>> findAllByCareer(@PathVariable Long id, @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(jobService.findAllByCareer(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/find-by-city/{id}")
    public ResponseEntity<Page<Job>> findAllByCity(@PathVariable Long id, @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(jobService.findAllByCity(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/find-by-employeeType/{id}")
    public ResponseEntity<Page<Job>> findAllByEmployeeType(@PathVariable Long id, @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(jobService.findALlByEmployeeType(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/find-by-salary/{min}/{max}")
    public ResponseEntity<List<Job>> findAllBySalary(@PathVariable("min") Double min, @PathVariable("max") Double max) {
        return new ResponseEntity<>(jobService.findBySalary(min, max), HttpStatus.OK);
    }

    @PostMapping("/search-all")
    public ResponseEntity<List<Job>> searchAll(@RequestBody SearchAll searchAll) {
        return new ResponseEntity<>(jobService.searchAllFields(searchAll), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Job job, @PathVariable Long id) {
        job.setStartDate(java.time.LocalDate.now());
        Job jobUpdate = jobService.findOne(id);
        if (jobUpdate != null) {
            jobService.save(job);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/findByKeyWord")
    public ResponseEntity<List<Job>> findByKeyWord(@RequestBody SearchKey searchKey) {
        List<Job> jobs = jobService.searchAllByKey(searchKey.getKey1(), searchKey.getKey2());
        jobService.removeJob(jobs);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping("/sort")
    public ResponseEntity<List<Job>> sort(@RequestParam String sort) {
        return new ResponseEntity<>(jobService.sort(sort), HttpStatus.OK);
    }

}

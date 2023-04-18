package com.example.findjobbe.controller;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.model.SearchAll;
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

    //    @GetMapping
//    public ResponseEntity<Page<Job>> findAll(@PageableDefault(page = 5) Pageable pageable) {
//        return new ResponseEntity<> (jobService.findAll(pageable), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<List<Job>> findAllTest() {
        return new ResponseEntity<>(jobService.findAllTest(), HttpStatus.OK);
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
    public ResponseEntity<List<Job>> findAllByCompany(@PathVariable Long id) {
        return new ResponseEntity<>(jobService.findAllByCompany(id), HttpStatus.OK);
    }

    //Block Job
    @GetMapping("/blockOrUnlockJob/{id}")
    public ResponseEntity<List<Job>> blockOrUnlockJob(@PathVariable Long id) {
        Job jobBlock = jobService.findOne(id);
        if (jobBlock.getStatus()) {
            jobBlock.setStatus(false); //block job
        } else {
            jobBlock.setStatus(true); //unlock job
        }
        jobService.save(jobBlock);
        return new ResponseEntity<>(jobService.findAllTest(), HttpStatus.OK);
    }

    //Unlock job
    @GetMapping("/unlockJob/{id}")
    public ResponseEntity<List<Job>> unlockJob(@PathVariable Long id) {
        Job jobUnlock = jobService.findOne(id);
        jobUnlock.setStatus(true);
        jobService.save(jobUnlock);
        return new ResponseEntity<>(jobService.findAllTest(), HttpStatus.OK);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Page<Job>> findAllByJobName(@RequestParam("name") String name, @PageableDefault Pageable pageable) {
        if (!name.equals("")) {
            return new ResponseEntity<>(jobService.findAllByJobName(name, pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(jobService.findALl(pageable), HttpStatus.OK);
    }

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

    @GetMapping("/findByKeyWord/{key1}/{key2}")
    public ResponseEntity<List<Job>> findByKeyWord(@PathVariable String key1, @PathVariable String key2) {
        List<Job> jobs = jobService.searchAllByKey(key1, key2);
        if (jobs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        }
    }
}

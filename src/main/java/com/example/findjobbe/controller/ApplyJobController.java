package com.example.findjobbe.controller;

import com.example.findjobbe.model.ApplyJob;
import com.example.findjobbe.service.ApplyJob.ApplyJobService;
import jdk.internal.dynalink.linker.LinkerServices;
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
@RequestMapping("/action-jobs")
public class ApplyJobController {
    @Autowired
    ApplyJobService applyJobService;
    @PostMapping("/apply/{jobId}/{userId}")
    public ResponseEntity<Void> applyJob(@PathVariable("jobId") Long jobId,@PathVariable("userId")Long userId){
        if (applyJobService.applyJob(jobId,userId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelJob(@PathVariable Long id){
        if (applyJobService.cancelApplyJob(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/accept/{id}")
    public ResponseEntity<Void> acceptJob(@PathVariable Long id){
        if (applyJobService.acceptApplyJob(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/reject/{id}")
    public ResponseEntity<Void> rejectJob(@PathVariable Long id){
        if (applyJobService.rejectApplyJob(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/find-apply-job/{id}")
    public ResponseEntity<List<ApplyJob>> findALlByCompanyId(@PathVariable Long id){
        return new ResponseEntity<>(applyJobService.findAllByCompanyId(id),HttpStatus.OK);
    }
    @PostMapping("/find-user-apply-job/{id}")
    public ResponseEntity<Page<ApplyJob>> findALlByUserId(@PathVariable Long id, @PageableDefault(size = 5) Pageable pageable){
        return new ResponseEntity<>(applyJobService.findAllByUserId(id,pageable),HttpStatus.OK);
    }
}

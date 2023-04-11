package com.example.findjobbe.controller;

import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.Job;
import com.example.findjobbe.service.company.CompanyService;
import com.example.findjobbe.service.jobs.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<Page<Company>> findAll(@PageableDefault(page = 5) Pageable pageable) {
        return new ResponseEntity<> (companyService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(companyService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Company company) {
        companyService.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.example.findjobbe.controller;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private ICompanyService iCompanyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(iCompanyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(iCompanyService.findOne(id),HttpStatus.OK);
    }

}

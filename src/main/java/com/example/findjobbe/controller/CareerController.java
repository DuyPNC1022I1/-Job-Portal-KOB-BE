package com.example.findjobbe.controller;

import com.example.findjobbe.model.Career;
import com.example.findjobbe.model.Job;
import com.example.findjobbe.service.carrer.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/career")
public class CareerController {
    @Autowired
    private CareerService careerService;

    @GetMapping
    public ResponseEntity<List<Career>> findAll() {
        List<Career> careers = careerService.findAll();
        return new ResponseEntity<>(careers, HttpStatus.OK);
    }
}

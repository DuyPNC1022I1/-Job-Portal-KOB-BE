package com.example.findjobbe.controller;

import com.example.findjobbe.model.Career;
import com.example.findjobbe.model.Employee;
import com.example.findjobbe.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}

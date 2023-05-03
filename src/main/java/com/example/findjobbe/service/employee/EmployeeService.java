package com.example.findjobbe.service.employee;

import com.example.findjobbe.model.Employee;
import com.example.findjobbe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService extends IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}

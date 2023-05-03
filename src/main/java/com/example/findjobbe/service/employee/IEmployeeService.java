package com.example.findjobbe.service.employee;

import com.example.findjobbe.model.Employee;
import com.example.findjobbe.service.ICoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IEmployeeService implements ICoreService<Employee> {
    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Employee findOne(Long id) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(Long id) {

    }
}

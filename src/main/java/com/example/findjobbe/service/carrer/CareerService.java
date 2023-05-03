package com.example.findjobbe.service.carrer;

import com.example.findjobbe.model.Career;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerService extends ICareerService{
    @Autowired
    private CareerRepository careerRepository;

    public List<Career> findAll() {
        return careerRepository.findAll();
    }
}

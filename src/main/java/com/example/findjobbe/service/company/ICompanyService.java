package com.example.findjobbe.service.company;

import com.example.findjobbe.model.Company;
import com.example.findjobbe.repository.CompanyRepository;
import com.example.findjobbe.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICompanyService extends CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}

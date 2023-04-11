package com.example.findjobbe.service.company;

import com.example.findjobbe.model.Company;
import com.example.findjobbe.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Page<Company> findAll(String name, Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Company findOne(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}

package com.example.findjobbe.service;

import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.User;
import com.example.findjobbe.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICoreService<Company>{
    @Autowired
    CompanyRepository companyRepository;
    public Company findCompanyByAccount(Account account){
        return companyRepository.findCompanyByAccount(account);
    }
    @Override
    public Page<Company> findAll(Pageable pageable) {
        return null;
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

package com.example.findjobbe.service.company;

import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.User;
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

    public Boolean updateCompany(Company companyUpdate){
        Company company = findOne(companyUpdate.getId());
        if(company!=null){
            if (!companyUpdate.getAccount().getName().equals("")){
                company.getAccount().setName(companyUpdate.getAccount().getName());
            }
            if (!companyUpdate.getGoogleMapLink().equals("")){
                company.setGoogleMapLink(companyUpdate.getGoogleMapLink());
            }
            if (!companyUpdate.getImagePath().equals("")){
                company.setImagePath(companyUpdate.getImagePath());
            }
            if (!companyUpdate.getPhoneNumber().equals("")){
                company.setPhoneNumber(companyUpdate.getPhoneNumber());
            }
            if (!companyUpdate.getDescription().equals("")){
                company.setDescription(companyUpdate.getDescription());
            }
            if (!companyUpdate.getAddress().equals("")){
                company.setAddress(companyUpdate.getAddress());
            }
            if (!companyUpdate.getCity().getName().equals("")){
                company.getCity().setName(companyUpdate.getCity().getName());
            }
            save(company);
            return true;
        }
        return false;
    }

}

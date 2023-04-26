package com.example.findjobbe.service;

import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.Job;
import com.example.findjobbe.repository.CompanyRepository;
import com.example.findjobbe.service.jobs.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements ICoreService<Company>{
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    JobService jobService;
    public Company findCompanyByAccount(Account account){
        return companyRepository.findCompanyByAccount(account);
    }
    @Override
    public Page<Company> findAll(Pageable pageable) {
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

    public Long countQuantity(Company company){
        Long count = 0L;
        List<Job> jobList = jobService.findAllByCompany(company.getId());
        for (Job j:jobList){
            count+=j.getQuantity();
        }
        return count;
    }
    public Long findMax(List<Long> list){
        Long max = 0L;
        for (Long l:list){
            if (l >= max){
                max = l;
            }
        }
        return max;
    }

    public List<Company> topHighRecruit(){
        List<Long> listCount = new ArrayList<>();
        List<Company> companyTopList = new ArrayList<>();
        List<Company> companyList= companyRepository.findAll();
        for (Company c:companyList){
            Long count = countQuantity(c);
            if (count!=0L){
                listCount.add(count);
            }

        }
        while (companyTopList.size()<6 && !listCount.isEmpty()){
        for (Company c:companyList){
            Long max = findMax(listCount);
            if (countQuantity(c)>=max && max!=0L){
                if (!companyTopList.contains(c)){
                companyTopList.add(c);
                listCount.remove(countQuantity(c));
                }
            }
        }
        }
        return companyTopList;
    }

}

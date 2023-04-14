package com.example.findjobbe.service.jobs;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService extends ICoreServiceJob{
    @Autowired
    private JobRepository jobRepository;
    public List<Job> findAllTest() {
        return jobRepository.findAll();
    }
    public List<Job> findAllByCompany(Long companyId) {
        return jobRepository.findAllByCompany_Id(companyId);
    }
    public List<Job> findAllByJobName(String name){
        return jobRepository.findAllJobByName(name);
    }
    public List<Job> findALlByCompanyAddress(String address){
        return jobRepository.findAllJobByCompanyAddress(address);
    }
    public List<Job> findAllByCareer(Long id){
        return jobRepository.findAllByCareer_Id(id);
    }
    public List<Job> findAllByCity(Long id){
        return jobRepository.findAllByCompany_City_Id(id);
    }
    public List<Job> findALlByEmployeeType(Long id){
        return jobRepository.findAllByEmployeeType_Id(id);
    }
    public List<Job> findBySalary(Double min,Double max){
        List<Job> jobs = findAllTest();
        List<Job> findList = new ArrayList<>();
        for (Job j:jobs) {
            if (j.getSalaryMin()<=min || j.getSalaryMax()<=max){
                findList.add(j);
            }
        }
        return findList;
    }

}

package com.example.findjobbe.service.jobs;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.model.Search;
import com.example.findjobbe.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Job> findALl(Pageable pageable){
        return jobRepository.findAll(pageable);
    }
    public List<Job> findAllByCompany(Long companyId) {
        return jobRepository.findAllByCompany_Id(companyId);
    }
    public Page<Job> findAllByJobName(String name, Pageable pageable){
        return jobRepository.findAllJobByName(name,pageable);
    }
    public Page<Job> findALlByCompanyAddress(String address,Pageable pageable){
        return jobRepository.findAllJobByCompanyAddress(address,pageable);
    }
    public Page<Job> findAllByCareer(Long id,Pageable pageable){
        return jobRepository.findAllByCareer_Id(id,pageable);
    }
    public Page<Job> findAllByCity(Long id,Pageable pageable){
        return jobRepository.findAllByCompany_City_Id(id,pageable);
    }
    public Page<Job> findALlByEmployeeType(Long id,Pageable pageable){
        return jobRepository.findAllByEmployeeType_Id(id,pageable);
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

    public void copy(List<Job> jobList,List<Job> list){
        for (Job j:list) {
            if (!jobList.contains(j)){
                jobList.add(j);
            }
        }
    }

    public List<Job>search(Search search){
        List<Job> searchList = new ArrayList<>();
        if (search.getExpiration()!=null){
            List<Job> listFind = jobRepository.findAllByExpiration(search.getExpiration());
            copy(searchList,listFind);
        }
        if (search.getCity_id()!=null){
            List<Job> jobList = jobRepository.findAllByCompany_City_Id(search.getCity_id());
            copy(searchList,jobList);
        }
        if (search.getTypeTime()!=null){
            List<Job> jobList = jobRepository.findAllByTypeTime(search.getTypeTime());
            copy(searchList,jobList);
        }
        if (search.getEmployeeType_id()!=null){
            List<Job> jobList = jobRepository.findAllByEmployeeType_Id(search.getEmployeeType_id());
            copy(searchList,jobList);
        }
        if (search.getSalaryMin()!=null && search.getSalaryMax()!=null){
            List<Job> jobList = findBySalary(search.getSalaryMin(), search.getSalaryMax());
            copy(searchList,jobList);
        }
        if (search.getGender()!=null){
            List<Job> jobList = jobRepository.findAllByGender(search.getGender());
            copy(searchList,jobList);
        }
        return searchList;
    }

}

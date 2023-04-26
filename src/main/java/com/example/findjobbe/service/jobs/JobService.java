package com.example.findjobbe.service.jobs;

import com.example.findjobbe.model.ApplyJob;
import com.example.findjobbe.model.Job;
import com.example.findjobbe.model.SearchAll;
import com.example.findjobbe.repository.ApplyJobRepository;
import com.example.findjobbe.repository.JobRepository;
import com.example.findjobbe.service.ApplyJob.ApplyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService extends ICoreServiceJob {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> findAllTest() {
        return jobRepository.findAll();
    }

    public Page<Job> findALl(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    public List<Job> findAllByCompany(Long companyId) {
        return jobRepository.findAllByCompany_Id(companyId);
    }

    public Page<Job> findAllByJobName(String name, Pageable pageable) {
        return jobRepository.findAllJobByName(name, pageable);
    }

    public Page<Job> findALlByCompanyAddress(String address, Pageable pageable) {
        return jobRepository.findAllJobByCompanyAddress(address, pageable);
    }

    public Page<Job> findAllByCareer(Long id, Pageable pageable) {
        return jobRepository.findAllByCareer_Id(id, pageable);
    }

    public Page<Job> findAllByCity(Long id, Pageable pageable) {
        return jobRepository.findAllByCompany_City_Id(id, pageable);
    }

    public Page<Job> findALlByEmployeeType(Long id, Pageable pageable) {
        return jobRepository.findAllByEmployeeType_Id(id, pageable);
    }

    public List<Job> findBySalary(Double min, Double max) {
        List<Job> jobs = findAllTest();
        List<Job> findList = new ArrayList<>();
        for (Job j : jobs) {
            if (j.getSalaryMin() <= min || j.getSalaryMax() <= max) {
                findList.add(j);
            }
        }
        return findList;
    }

    public void copy(List<Job> jobList, List<Job> list) {
        for (Job j : list) {
            if (!jobList.contains(j)) {
                jobList.add(j);
            }
        }
    }


    public Double[] checkSalary(Long salary) {
        Double[] minMax = new Double[2];
        if (salary == 0) {
            minMax[0] = 0D;
            minMax[1] = 1000D;
        }
        if (salary == 1) {
            minMax[0] = 1000D;
            minMax[1] = 2000D;
        }
        if (salary == 2) {
            minMax[0] = 2000D;
            minMax[1] = 3000D;
        }
        if (salary == 3) {
            minMax[0] = 3000D;
            minMax[1] = 4000D;
        }
        if (salary == 4) {
            minMax[0] = 4000D;
            minMax[1] = 5000D;
        }
        if (salary == 5) {
            minMax[0] = 5000D;
            minMax[1] = 500000000D;
        }

        return minMax;
    }

    public List<Job> searchAllFields(SearchAll searchAll) {
        Integer count = 0;
        List<Job> searchList = new ArrayList<>();
        List<String> expiration = searchAll.getExpiration();
        if (!expiration.isEmpty()) {
            for (String e : expiration) {
                List<Job> listFind = jobRepository.findAllByExpiration(Long.parseLong(e));
                copy(searchList, listFind);
            }
        } else {
            count++;
        }
        List<String> cities = searchAll.getCity();
        if (!cities.isEmpty()) {
            for (String c : cities) {
                List<Job> listFind = jobRepository.findAllByCompany_City_Id(Long.parseLong(c));
                copy(searchList, listFind);
            }
        } else {
            count++;
        }
        List<String> typeTime = searchAll.getTypeTime();
        if (!typeTime.isEmpty()) {
            for (String t : typeTime) {
                List<Job> listFind = jobRepository.findAllByTypeTime(t);
                copy(searchList, listFind);
            }
        } else {
            count++;
        }
        List<String> gender = searchAll.getGender();
        if (!gender.isEmpty()) {
            for (String g : gender) {
                List<Job> listFind = jobRepository.findAllByGender(g);
                copy(searchList, listFind);
            }
        } else {
            count++;
        }
        List<String> salaries = searchAll.getSalary();
        if (!salaries.isEmpty()) {
            for (String s : salaries) {
                Double min = checkSalary(Long.parseLong(s))[0];
                Double max = checkSalary(Long.parseLong(s))[1];
                List<Job> listFind = findBySalary(min, max);
                copy(searchList, listFind);
            }
        } else {
            count++;
        }
        if (count == 5) {
            searchList = findAllTest();

        }
        return searchList;
    }

    public List<Job> searchAllByKey(String key1, String key2) {
        List<Job> jobsByKeyWord = new ArrayList<>();
        if (!key2.equals("") && !key1.equals("")) {
            List<Job> jobs = jobRepository.findAllByCompany_City_Id(Long.parseLong(key2));
            for (Job j : jobs) {
                if (j.getCompany().getAccount().getName().toUpperCase().contains(key1.toUpperCase())) {
                    List<Job> findList = new ArrayList<>();
                    findList.add(j);
                    copy(jobsByKeyWord, findList);
                }
                if (j.getEmployeeType().getName().toUpperCase().contains(key1.toUpperCase())) {
                    List<Job> findList = new ArrayList<>();
                    findList.add(j);
                    copy(jobsByKeyWord, findList);
                }
                if (j.getGender().equalsIgnoreCase(key1)) {
                    List<Job> findList = new ArrayList<>();
                    findList.add(j);
                    copy(jobsByKeyWord, findList);
                }
            }
        } else {
            if (!key1.equals("")) {
                List<Job> jobs = jobRepository.findAll();
                List<Job> findList = new ArrayList<>();
                for (Job j : jobs) {
                    if (j.getCompany().getAccount().getName().toUpperCase().contains(key1.toUpperCase())) {
                        findList.add(j);
                        copy(jobsByKeyWord,findList);
                    }
                    if (j.getEmployeeType().getName().toUpperCase().contains(key1.toUpperCase())) {
                        findList.add(j);
                        copy(jobsByKeyWord,findList);
                    }
                    if (j.getGender().equalsIgnoreCase(key1)) {
                        findList.add(j);
                        copy(jobsByKeyWord,findList);
                    }
                }

                return jobsByKeyWord;
            }
            if (!key2.equals("")) {
                jobsByKeyWord = jobRepository.findAllByCompany_City_Id(Long.parseLong(key2));
                return jobsByKeyWord;
            }
            jobsByKeyWord = jobRepository.findAll();

        }
        return jobsByKeyWord;
    }

    public List<Job> sort(String sort){
        if (sort.equals("newest")){
            return jobRepository.findAllByOrderByIdDesc();
        }
        if (sort.equals("oldest")){
            return jobRepository.findAllByOrderByIdAsc();
        }
        if (sort.equals("salaryMin")){
            return jobRepository.findAllByOrderBySalaryMaxAsc();
        }
        if (sort.equals("salaryMax")){
            return jobRepository.findAllByOrderBySalaryMaxDesc();
        }
        return findAllTest();
    }

    //Xử lý ẩn job-post có status=false
    public List<Job> removeJob(List<Job> jobs) {
        List<Job> jobsToRemove = new ArrayList<>();
        for (Job job : jobs) {
            if (!job.getStatus()) {
                jobsToRemove.add(job);
            }
        }
        jobs.removeAll(jobsToRemove);
        return jobs;
    }



}

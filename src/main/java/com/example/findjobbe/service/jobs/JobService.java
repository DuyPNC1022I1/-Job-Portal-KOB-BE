package com.example.findjobbe.service.jobs;

import com.example.findjobbe.model.Job;
import com.example.findjobbe.model.SearchAll;
import com.example.findjobbe.repository.JobRepository;
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
        if (salary == 1) {
            minMax[0] = 10000D;
            minMax[1] = 20000D;
        }
        if (salary == 2) {
            minMax[0] = 20000D;
            minMax[1] = 30000D;
        }
        if (salary == 3) {
            minMax[0] = 30000D;
            minMax[1] = 40000D;
        }
        if (salary == 4) {
            minMax[0] = 40000D;
            minMax[1] = 50000D;
        }
        if (salary == 5) {
            minMax[0] = 50000D;
            minMax[1] = 60000D;
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
        List<Job> jobs = jobRepository.findAll();
        List<Job> jobsByKeyWord = new ArrayList<>();
        for (int i = 0; i < jobs.size(); i++) {
            if (key1 != null && key2 != null) {
                if ((jobs.get(i).getCompany().getAccount().getName().toUpperCase().contains(key1.toUpperCase()))
                        && (jobs.get(i).getCity().getName().equals(key2))) {
                    jobsByKeyWord.add(jobs.get(i));
                } else if ((jobs.get(i).getGender().toUpperCase().contains(key1.toUpperCase())
                        && (jobs.get(i).getCity().getName().equals(key2)))) {
                    jobsByKeyWord.add(jobs.get(i));
                } else if ((jobs.get(i).getCareer().getName().toUpperCase().contains(key1.toUpperCase())
                        && (jobs.get(i).getCity().getName().equals(key2)))) {
                    jobsByKeyWord.add(jobs.get(i));
                } else {
                    System.out.println("no content!");
                }
            } else {
                if (jobs.get(i).getCompany().getAccount().getName().toUpperCase().contains(key1.toUpperCase())) {
                    jobsByKeyWord.add(jobs.get(i));
                } else if (jobs.get(i).getGender().toUpperCase().contains(key1.toUpperCase())) {
                    jobsByKeyWord.add(jobs.get(i));
                } else if (jobs.get(i).getCity().getName().equals(key2)) {
                    jobsByKeyWord.add(jobs.get(i));
                } else if (jobs.get(i).getCareer().getName().toUpperCase().contains(key1.toUpperCase())) {
                    jobsByKeyWord.add(jobs.get(i));
                } else {
                    System.out.println("no content!");
                }
            }
        }
        return jobsByKeyWord;
    }
    public List<Job> findAllByIdDesc(){
        return jobRepository.findAllByIdOrderByIdDesc();
    }
    public List<Job> findAllByIdAsc(){
        return jobRepository.findAllByIdOrderByIdAsc();
    }
    public List<Job> findAllBySalaryDesc(){
        return jobRepository.findAllBySalaryMaxOrderBySalaryMaxDesc();
    }
    public List<Job> findAllBySalaryAsc(){
        return jobRepository.findAllBySalaryMaxOrderBySalaryMaxAsc();
    }
}



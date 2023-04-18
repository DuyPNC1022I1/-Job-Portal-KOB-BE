package com.example.findjobbe.repository;

import com.example.findjobbe.model.Career;
import com.example.findjobbe.model.City;
import com.example.findjobbe.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAllByCompany_Id(Long companyId);
    List<Job> findAllByCareer_Id(Long id);
    @Query("SELECT j FROM Job j WHERE j.company.account.name like : name")
    List<Job> findAllJobByName(@Param("name") String name);
    @Query("SELECT j FROM Job j WHERE j.company.address like : address")
    List<Job> findAllJobByCompanyAddress(@Param("address") String address);
    List<Job> findAllByCompany_City_Id(Long id);
    List<Job> findAllByEmployeeType_Id(Long id);

}

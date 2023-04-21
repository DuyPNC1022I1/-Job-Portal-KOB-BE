package com.example.findjobbe.repository;

import com.example.findjobbe.model.ApplyJob;
import com.example.findjobbe.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long> {
    public boolean existsByJob_IdAndUser_Id(Long jobId,Long userId);
    public ApplyJob findApplyJobByJob_IdAndUser_Id(Long jobId,Long userId);
    public List<ApplyJob> findAllByJob_Id(Long id);
    public List<ApplyJob> findAllByJob_Company_Id(Long id);

}

package com.example.findjobbe.repository;

import com.example.findjobbe.model.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long> {
}

package com.example.findjobbe.repository;

import com.example.findjobbe.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    public List<Notification> findAllByCompany_IdOrderByIdDesc(Long id);
    public List<Notification> findAllByUser_IdOrderByIdDesc(Long id);

}

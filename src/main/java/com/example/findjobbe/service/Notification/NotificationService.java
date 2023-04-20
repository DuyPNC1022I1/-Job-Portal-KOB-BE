package com.example.findjobbe.service.Notification;

import com.example.findjobbe.model.Notification;
import com.example.findjobbe.repository.NotificationRepository;
import com.example.findjobbe.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements ICoreService<Notification> {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Page<Notification> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Notification findOne(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }
}

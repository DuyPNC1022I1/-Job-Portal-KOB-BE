package com.example.findjobbe.service.Notification;

import com.example.findjobbe.model.Notification;
import com.example.findjobbe.repository.NotificationRepository;
import com.example.findjobbe.service.CompanyService;
import com.example.findjobbe.service.ICoreService;
import com.example.findjobbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements ICoreService<Notification> {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    CompanyService companyService;
    @Autowired
    UserService userService;

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

    public List<Notification> findAllById(Long id){
        if (companyService.findOne(id)!=null){
            return notificationRepository.findAllByCompany_Id(id);
        }
        return notificationRepository.findAllByUser_Id(id);
    }


}

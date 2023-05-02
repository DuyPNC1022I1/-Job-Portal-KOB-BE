package com.example.findjobbe.service.Notification;

import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.Notification;
import com.example.findjobbe.model.User;
import com.example.findjobbe.repository.NotificationRepository;
import com.example.findjobbe.service.AccountService;
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
    @Autowired
    AccountService accountService;

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

            return notificationRepository.findAllByAccount_IdOrderByIdDesc(id);
    }
    public void readNotification(Long id){
        Notification notification = notificationRepository.findById(id).orElse(null);
        if (notification!=null){
            notification.setStatus(false);
            notificationRepository.save(notification);
        }
    }
    public Boolean companyViewUser(Long userId,Long companyId){
        User user = userService.findOne(userId);
        Company company = companyService.findOne(companyId);
        if (user!= null && company!=null){
            Notification notification = new Notification();
            String text =company.getAccount().getName() +
                    " viewed your cv ";
            notification.setText(text);
            notification.setAccount(user.getAccount());
            notification.setStatus(true);
            save(notification);
            return true;
        }
        return false;
    }


}

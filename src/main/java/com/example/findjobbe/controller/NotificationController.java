package com.example.findjobbe.controller;

import com.example.findjobbe.model.Notification;
import com.example.findjobbe.service.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @PostMapping("/{id}")
    public ResponseEntity<List<Notification>> getNotificationById(@PathVariable Long id){
        return new ResponseEntity<>(notificationService.findAllById(id), HttpStatus.OK);
    }


}

package com.example.findjobbe.controller;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.TopCompany;
import com.example.findjobbe.model.User;
import com.example.findjobbe.service.Notification.NotificationService;
import com.example.findjobbe.service.UserService;
import com.example.findjobbe.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private ICompanyService iCompanyService;
    @Autowired
    private UserService userService;
    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Page<Company>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(iCompanyService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(iCompanyService.findOne(id),HttpStatus.OK);
    }
    @PostMapping("/top-company")
    public ResponseEntity<List<TopCompany>> topCompany(){
        return new ResponseEntity<>(iCompanyService.topHighRecruit(),HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> save(@RequestBody Company company) {
        if (iCompanyService.updateCompany(company)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/view-user/{companyId}/{userId}")
    public ResponseEntity<User> companyViewUser(@PathVariable Long companyId,@PathVariable Long userId){
        if (notificationService.companyViewUser(userId,companyId)){
            return new ResponseEntity<>(userService.findOne(userId),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

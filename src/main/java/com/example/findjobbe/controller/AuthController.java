package com.example.findjobbe.controller;

import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.User;
import com.example.findjobbe.service.AccountService;
import com.example.findjobbe.service.CompanyService;
import com.example.findjobbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Account account) {
        if (accountService.register(account)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        String message = "Account is exist";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/active/{email}")
    public ResponseEntity<Void> active(@PathVariable String email){
        if (accountService.activeAccount(email)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
   @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Account account){
        String email = account.getEmail();
        Account accountFind = accountService.findAccountByEmail(email);
        if (account.getPassword().equals(accountFind.getPassword())){
            if (accountFind.getRoles().equals("company")){
                return new ResponseEntity<>(companyService.findCompanyByAccount(accountFind),HttpStatus.OK);
            }
            else{
              return new ResponseEntity<>(userService.findUserByAccount(accountFind),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  @PostMapping("/forget-password/{email}")
    public ResponseEntity<Void> forgetPassword(@PathVariable String email){
        Account account = accountService.findAccountByEmail(email);
        if (account.getStatus()){
            String to = account.getEmail();
            String subject = "Reset password from KOB find job";
            String code = accountService.generateRandomCode();
            String text = "Hello, "+account.getEmail()+"\n Your password has been reset to " +code+", please change it. Thank you";
            account.setPassword(code);
            accountService.save(account);
            accountService.sendMail(to,subject,text);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }


}

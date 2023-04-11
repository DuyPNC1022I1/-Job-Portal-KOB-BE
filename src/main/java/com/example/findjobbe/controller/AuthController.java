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
    public ResponseEntity<String> register(@RequestBody Account account){
        String email = account.getEmail();
        String link = "http://localhost:8080/active/"+account.getEmail();
        String subject = "Active account off KOB find job";
        String text = "Hello" +account.getEmail() + "Please confirm this link to active your account :";
        if (accountService.findAccountByEmail(email)==null){
            account.setStatus(false);
            accountService.save(account);
            Account accountAdd = accountService.findAccountByEmail(email);
            if (account.getRoles().equals("user")){
                User user = new User();
                user.setAccount(accountAdd);
                userService.save(user);

            }else {
                Company company = new Company();
                company.setAccount(accountAdd);
                companyService.save(company);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }
        String message = "Account is exist";
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
//   @PostMapping("/login")
//    public ResponseEntity<Void> login(@RequestParam("email")String email,
//                                      @RequestParam("password") String password){
//        Account account = accountService.findAccountByEmail(email);
//        if (account.getPassword().equals(password)){
//
//        }
//
//    }
}

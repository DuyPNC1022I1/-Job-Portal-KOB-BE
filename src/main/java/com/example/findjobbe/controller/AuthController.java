package com.example.findjobbe.controller;

import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.Password;
import com.example.findjobbe.service.AccountService;
import com.example.findjobbe.service.CompanyService;
import com.example.findjobbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView active(@PathVariable String email){
        if (accountService.activeAccount(email)){
            return new RedirectView("http://localhost:3000");
        }
        return new RedirectView("http://localhost:3000/404");
    }
   @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Account account){

        String email = account.getEmail();
        Account accountFind = accountService.findAccountByEmail(email);
        if (accountFind.getStatus()){
        if (account.getPassword().equals(accountFind.getPassword())){
            if (accountFind.getRoles().equals("company")){
                return new ResponseEntity<>(companyService.findCompanyByAccount(accountFind),HttpStatus.OK);
            }
            else{
              return new ResponseEntity<>(userService.findUserByAccount(accountFind),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  @PostMapping("/forget-password")
    public ResponseEntity<Void> forgetPassword(@RequestParam("email") String email){
        Account account = accountService.findAccountByEmail(email);
        if (account.getStatus()){
            String to = account.getEmail();
            String subject = "Reset password from KOB find job";
            String code = accountService.generateRandomCode();
            String text = "Hello, "+account.getName()+"\n Your password has been reset to " +code+", please change it. Thank you";
            account.setPassword(code);
            accountService.save(account);
            accountService.sendMail(to,subject,text);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  @GetMapping("/{id}")
    public ResponseEntity<Account> findOne(@PathVariable Long id){
        return new ResponseEntity<>(accountService.findOne(id),HttpStatus.OK);
  }
  @PostMapping("/change-password")
  public ResponseEntity<Void> changePassword(@RequestBody Password password){
     if (accountService.changePassword(password)){
         return new ResponseEntity<>(HttpStatus.OK);
     }
     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

}

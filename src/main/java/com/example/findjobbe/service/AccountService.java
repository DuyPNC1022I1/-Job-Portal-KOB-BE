package com.example.findjobbe.service;


import com.example.findjobbe.model.Account;
import com.example.findjobbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AccountRepository accountRepository;
    public Boolean activeAccount(String email){
        if (accountRepository.existsAccountByEmail(email)){
            Account account = accountRepository.findByEmail(email);
            account.setStatus(true);
            return true;
        }
        return false;
    }
    public void sendMail(String to,String subject,String text){
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
    public void createAccount(Account account){

    }



}

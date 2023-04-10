package com.example.findjobkob.service;

import com.example.findjobkob.model.Account;
import com.example.findjobkob.model.Company;
import com.example.findjobkob.model.User;
import com.example.findjobkob.repository.AccountRepository;
import com.example.findjobkob.repository.CompanyRepository;
import com.example.findjobkob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ActiveService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(@RequestParam("to") String email,
                          @RequestParam("subject") String subject,
                          @RequestParam("text") String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }

    public Boolean activeAccount(String email) {
        if (accountRepository.existsAccountByEmail(email)) {
            Account account = accountRepository.findByEmail(email);
            account.setStatus(true);
            return true;
        }
        return false;
    }

}

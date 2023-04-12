package com.example.findjobbe.service;


import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.Company;
import com.example.findjobbe.model.User;
import com.example.findjobbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService implements ICoreService<Account> {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    public Boolean activeAccount(String email){
        Account account = accountRepository.findByEmail(email);
        if (account!=null){
            account.setStatus(true);
            accountRepository.save(account);
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
    public Account findAccountByEmail(String email){
        return accountRepository.findByEmail(email);
    }
    public Boolean register(Account account) {
        String email = account.getEmail();
        String link = "http://localhost:8080/auth/active/" + email;
        String subject = "Active account from KOB find job";
        String text = "Hello, " + account.getName()
                     + "\n Please confirm this link to active your account: "+link;
        if (findAccountByEmail(email) == null && account.getRoles()!=null) {
            account.setStatus(false);
            save(account);
            Account accountAdd = findAccountByEmail(email);
            if (account.getRoles().equals("user")) {
                User user = new User();
                user.setAccount(accountAdd);
                userService.save(user);

            } else {
                Company company = new Company();
                company.setAccount(accountAdd);
                companyService.save(company);
            }
            sendMail(email,subject,text);
            return true;
        }
        return false;
    }

    public String generateRandomCode() {
        int length = 8;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }


    @Override
    public void save(Account account) {
            accountRepository.save(account);
    }
    @Override
    public Page<Account> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Account findOne(Long id) {
        return accountRepository.findById(id).orElse(null);
    }


    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);

    }
}

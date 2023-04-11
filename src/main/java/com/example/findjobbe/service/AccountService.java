package com.example.findjobbe.service;


import com.example.findjobbe.model.Account;
import com.example.findjobbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements ICoreService<Account> {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AccountRepository accountRepository;
    public Boolean activeAccount(String email){
        Account account = accountRepository.findByEmail(email);
        if (account!=null){
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
    public Account findAccountByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    @Override
    public void save(Account account) {
            accountRepository.save(account);
    }
    @Override
    public Page<Account> findAll(String name, Pageable pageable) {
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

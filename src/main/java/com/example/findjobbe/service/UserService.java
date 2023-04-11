package com.example.findjobbe.service;

import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.User;
import com.example.findjobbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ICoreService<User>{
    @Autowired
    UserRepository userRepository;
    public User findUserByAccount(Account account){
        return userRepository.findUserByAccount(account);
    }
    @Override
    public Page<User> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}

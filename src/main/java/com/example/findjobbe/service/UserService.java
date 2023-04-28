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
    public Page<User> findAll(Pageable pageable) {
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
    public Boolean updateUser(User userUpdate){
        User user = findOne(userUpdate.getId());
        if(user!=null){
            if (!userUpdate.getAccount().getName().equals("")){
                user.getAccount().setName(userUpdate.getAccount().getName());
            }
            if (!userUpdate.getBirthDay().equals("")){
                user.setBirthDay(userUpdate.getBirthDay());
            }
            if (!userUpdate.getDescription().equals("")){
                user.setDescription(userUpdate.getDescription());
            }
            if (!userUpdate.getAddress().equals("")){
                user.setAddress(userUpdate.getAddress());
            }
            if (!userUpdate.getCvPath().equals("")){
                user.setCvPath(userUpdate.getCvPath());
            }
            if (!userUpdate.getGender().equals("")){
                user.setGender(userUpdate.getGender());
            }
            if (!userUpdate.getImagePath().equals("")){
                user.setImagePath(userUpdate.getImagePath());
            }
            if (!userUpdate.getPhoneNumber().equals("")){
                user.setPhoneNumber(userUpdate.getPhoneNumber());
            }
            save(user);
            return true;
        }
        return false;
    }

}

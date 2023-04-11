package com.example.findjobbe.repository;

import com.example.findjobbe.model.Account;
import com.example.findjobbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByAccount(Account account);

}

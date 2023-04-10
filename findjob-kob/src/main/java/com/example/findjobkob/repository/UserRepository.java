package com.example.findjobkob.repository;

import com.example.findjobkob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByEmail(String email);
    public Boolean existsByEmail(String email);

}

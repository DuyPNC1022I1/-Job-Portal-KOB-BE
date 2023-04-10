package com.example.findjobkob.repository;

import com.example.findjobkob.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public Boolean existsAccountByEmail(String email);
    public Account findByEmail(String email);

}

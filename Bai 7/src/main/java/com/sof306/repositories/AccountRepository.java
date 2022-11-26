package com.sof306.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sof306.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	
}

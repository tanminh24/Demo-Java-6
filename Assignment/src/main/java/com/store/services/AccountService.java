package com.store.services;

import java.util.List;

import com.store.entities.Account;

public interface AccountService {

	Account findByUsername(String username);

	List<Account> getAdministrators();

	List<Account> findAll();

	Account create(Account acc);

	Account findAccByToken(String token);

}

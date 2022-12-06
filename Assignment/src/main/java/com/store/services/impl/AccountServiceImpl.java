package com.store.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.Account;
import com.store.reponsitories.AccountRepository;
import com.store.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepo;

	@Override
	public List<Account> getAdministrators() {
		return accountRepo.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		return accountRepo.findAll();
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepo.findByUsername(username);
	}

	@Override
	public Account create(Account acc) {
		return accountRepo.save(acc);
	}

	@Override
	public Account findAccByToken(String token) {
		return accountRepo.findAccByToken(token);
	}

}
